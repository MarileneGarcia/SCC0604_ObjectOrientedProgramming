



/*
	Algoritmo evolutivo para um swarm simulado

	Comando para instalar a OpenGL no Ubuntu: 
			sudo apt-get install freeglut3-dev 
	Comando para compilar o código (sendo "codeName.c" o nome do código e "executableName" o nome desejado para o executável): 
			g++ codeName.cpp -lGL -lGLU -lglut -lm -o executableName
	Rodar o executável:
			./executableName

*/


#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <unistd.h>
#include <GL/glut.h>
#include "formigueiro.h"

using namespace std;

static int win(0); //Função da OpenGL

void printScenario(PIXEL**);

void setScenario(PIXEL**);

void beginPopulation(PIXEL**, FORMIGUEIRO*);

void runGeneration(PIXEL**, FORMIGUEIRO*);

void avalueGeneration(FORMIGUEIRO*);

void crossover(FORMIGUEIRO*);

void mutation(FORMIGUEIRO*);

void rebootPopulation(PIXEL**, FORMIGUEIRO*);

int main(int argc, char* argv[])
{
	time_t t; srand((unsigned) time(&t)); //Renova a semente da função rand() para evitar repetição dos números sorteados

	int i,j; //Variáveis Auxiliares
	int geracao, loop;

	// Alocação Dinâmica da Matriz que armazena os pixeis
	PIXEL **MatrizPixeis = (PIXEL**)malloc(LN_SCREEN * sizeof(PIXEL*)); 
	for (i=0; i<LN_SCREEN; i++)
		MatrizPixeis[i] = (PIXEL*) malloc(H_SCREEN * sizeof(PIXEL));
		
	// Formigueiros
	FORMIGUEIRO *formigueiro = (FORMIGUEIRO*)malloc(N_FORMIGUEIROS * sizeof(FORMIGUEIRO));

	//Funções e procedimentos da OpenGL
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowPosition(0, 0);
	glutInitWindowSize(LN_SCREEN, H_SCREEN);
	glutCreateWindow("Ants");
	glMatrixMode( GL_PROJECTION );
	glLoadIdentity();
	glClear(GL_COLOR_BUFFER_BIT);
	gluOrtho2D(0.0, (float) LN_SCREEN, (float) H_SCREEN, 0.0);

	setScenario(MatrizPixeis);

	beginPopulation(MatrizPixeis, formigueiro);

	printScenario(MatrizPixeis);

	glFlush();

	getchar();

	printf("\e[H\e[2J");

	for(geracao=0; geracao<40; geracao++)
	{
		cout<< endl << "****************************************Geracao " << geracao+1 << endl << endl;

		for(int k=0; k<N_FORMIGUEIROS; k++)
		{
			cout<<endl<<" > Formigueiro "<<formigueiro[k].indice<< "   ( " << formigueiro[k].fila_formigas->tamanho << "  formigas  ) " <<endl;
			cout<<"	->DNA: ";
			for(i=0; i<SIZE_DNA; i++)
				cout<<formigueiro[k].DNA[i]<<" |";
			cout<<endl;
		}

		getchar();


		for(loop=0; loop<LOOP_SIZE; loop++)
		{
			printScenario(MatrizPixeis);
			
			runGeneration(MatrizPixeis, formigueiro);

			glFlush();
		}

		avalueGeneration(formigueiro);

		crossover(formigueiro);

		mutation(formigueiro);

		getchar();

		//rebootPopulation(MatrizPixeis, formigueiro);

		printf("\e[H\e[2J");
	}
	
	
	glutMainLoop();	
}


void printScenario(PIXEL** matriz)
{
	int i, j, k;
	
	for(i=0; i<LN_SCREEN; i++)
	{
		for(j=0; j<H_SCREEN; j++)
		{
			// Paredes
			if( matriz[i][j].type == P ) 
			{
				glBegin(GL_POINTS);
					glColor3f(0.0,0.0,0.0);
					glVertex2i(i,j);
				glEnd();
			}
			// Água
			else if( matriz[i][j].type == A )
			{
				glBegin(GL_POINTS);
					glColor3f(0.0,0.75,1.0);
					glVertex2i(i,j);
				glEnd();
			}
			// Formigas
			else if( matriz[i][j].type == F )
			{
				glBegin(GL_POINTS);
					glColor3f(1.0,0.0,0.0);
					glVertex2i(i,j);
				glEnd();
			}
			// Vazio
			else
			{	
				glBegin(GL_POINTS);
					glColor3f(0.95,1.0,0.95);
					glVertex2i(i,j);
				glEnd();
			}
		}
	}
}


void setScenario(PIXEL** matriz)
{
	int i, j, k;

	for(i=0; i<LN_SCREEN; i++)
	{
		for(j=0; j<H_SCREEN; j++)
		{
			// Paredes
			if( (i>=0 && i<10) || (i>=410 && i<420) || (i>=820 && i<830) || (i>=1230 && i<1240) || 
				(j>=0 && j<10) || (j>=310 && j<320) || (j>=620 && j<630) ) 
			{
				matriz[i][j].type = P;
				matriz[i][j].x = i;
				matriz[i][j].y = j;
				matriz[i][j].vida = -1;

				glBegin(GL_POINTS);
					glColor3f(0.0,0.0,0.0);
					glVertex2i(i,j);
				glEnd();
			}
			// Água
			else if( (i>=190 && i<240) || (i>=600 && i<650) || (i>=1010 && i<1060) )
			{
				matriz[i][j].type = A;
				matriz[i][j].x = i;
				matriz[i][j].y = j;
				matriz[i][j].vida = -1;

				glBegin(GL_POINTS);
					glColor3f(0.0,0.75,1.0);
					glVertex2i(i,j);
				glEnd();
			}
			// Vazio
			else
			{
				matriz[i][j].type = VAZIO;
				matriz[i][j].x = i;
				matriz[i][j].y = j;
				matriz[i][j].vida = -1;

				glBegin(GL_POINTS);
					glColor3f(0.95,1.0,0.95);
					glVertex2i(i,j);
				glEnd();
			}
		}
	}
}


void beginPopulation(PIXEL** matriz, FORMIGUEIRO* formigueiro)
{
	int i, j, k, m, n;
	int deltaI=0, deltaJ=0;

	for(k=0; k<N_FORMIGUEIROS; k++)
	{
		formigueiro[k].indice = k+1;

		for(i=0; i<SIZE_DNA; i++)
			formigueiro[k].DNA[i] = rand() % 6;

		formigueiro[k].fila_formigas = criar_fila();

		for(i=10+deltaI; i<190+deltaI; i++)
		{
			for(j=10+deltaJ; j<310+deltaJ; j++)
			{
				if( (rand() % 101 >= 80) &&
				(matriz[i+ANT_RATIO][j].type != F) && (matriz[i-ANT_RATIO][j].type != F) &&
				(matriz[i][j+ANT_RATIO].type != F) && (matriz[i][j-ANT_RATIO].type != F) &&
				(matriz[i+ANT_RATIO][j].type != P) && (matriz[i-ANT_RATIO][j].type != P) &&
				(matriz[i][j+ANT_RATIO].type != P) && (matriz[i][j-ANT_RATIO].type != P) )
				{
					matriz[i][j].vida = MAX_VIDA;
					for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
					{
						for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
						{
							if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) )
							{
								matriz[i+m][j+n].type = F;
								if(!(m==0 && n==0)) matriz[i+m][j+n].vida = -1;
							}
						}
							
					}
					enfileirar(formigueiro[k].fila_formigas, &matriz[i][j]);
				}
			}
		}

		
		if(k==2) 
		{
			deltaJ += 310;
			deltaI = 0;
		}
		else deltaI += 410;
	} 
}


void runGeneration(PIXEL** matriz, FORMIGUEIRO* formigueiro)
{
	int k, i, j;
	int decisao;
	int tamanho;
	float media = 0.0;
	int num_formigas;
	int x_max = 0;

	PIXEL* formiga_mover;
	VISAO* visao_formiga;
	for(k=0; k<N_FORMIGUEIROS; k++, media=0.0)
	{
		num_formigas = formigueiro[k].fila_formigas->tamanho;
		//cout<<endl<<"**** Formigueiro:  "<< k << endl; 
		//cout<<"Formigas no inicio:  " << num_formigas<<endl;
		for(tamanho = num_formigas; tamanho>0; tamanho--)
		{
			formiga_mover = desenfileirar(formigueiro[k].fila_formigas);
			//cout<<" Pixel em analise: "<< endl;
			//cout<< " > Coordenadas( " << formiga_mover->x << " , "<< formiga_mover->y << " ) " <<endl;
			
			visao_formiga = antVision(matriz, formiga_mover);

			decisao = antDecision(&formigueiro[k], visao_formiga);
			
			antMove(matriz, formiga_mover, visao_formiga, decisao);

			media = media + ( (float) formiga_mover->x / num_formigas);

			if(formiga_mover->x > x_max) x_max = formiga_mover->x;

			if( antLife(matriz, formiga_mover) == 1 )
				enfileirar(formigueiro[k].fila_formigas, formiga_mover);
		}

		//cout<<"Formigas no final:  " << formigueiro[k].fila_formigas->tamanho <<endl;
		formigueiro[k].x_medio = media;
		formigueiro[k].x_max = x_max;
		//cout<<"Formigueiro " << k+1 << " x medio: "<< media <<endl;
	}
}

void avalueGeneration(FORMIGUEIRO* formigueiro)
{
	int k;
	int i_esperado;
	
	cout<<" **************** Relatorio da geracao"<<endl;

	for(k=0, i_esperado=380; k<N_FORMIGUEIROS; k++)
	{
		formigueiro[k].fitness = 6* (  (formigueiro[k].x_medio - i_esperado + 400)  /  400 ) + 4* ( i_esperado - formigueiro[k].x_max  / (i_esperado - 10 ) );
		formigueiro[k].fitness = formigueiro[k].fitness / (float) 10;

		cout << endl << " > Formigueiro " << k+1 << ":" << endl;
		cout << "	-> X Medio: " << formigueiro[k].x_medio << endl;
		cout << "	-> Numero de Formigas: " << formigueiro[k].fila_formigas->tamanho << endl;
		cout << "	-> Fitness: " << formigueiro[k].fitness <<endl<<endl;

		if(k==2) i_esperado = 410;
		else i_esperado += 410;
	}
}

void crossover(FORMIGUEIRO* formigueiro)
{
	int k, m, i;
	int best = 0;

	cout<< "******Crossover "<<endl;

	for(k=0; k<N_FORMIGUEIROS; k++)
		if(formigueiro[k].fitness > best) best = k;

	for(k=0; k<N_FORMIGUEIROS; k++)
	{
		if(k != best)
		{
			if(rand()%2)
			{
				for(m=0; m<SIZE_DNA; m++)
					if(m%2 == 0) formigueiro[k].DNA[m] = formigueiro[best].DNA[m];
			}
			else
			{
				for(m=0; m<SIZE_DNA; m++)
					if(m%2 != 0) formigueiro[k].DNA[m] = formigueiro[best].DNA[m];
			}
		}

		cout<<" > DNA apos crossover:  ";
		for(i=0; i<SIZE_DNA; i++)
			cout<<formigueiro[k].DNA[i]<<" |";
		cout<<endl;
	}
}

void mutation(FORMIGUEIRO* formigueiro)
{
	int k, m, i, best = 0;

	cout<< "******Mutacoes "<<endl;

	for(k=0; k<N_FORMIGUEIROS; k++)
		if(formigueiro[k].fitness > best) best = k;

	for(k=0; k<N_FORMIGUEIROS; k++)
	{
		if(k!=best)
		{
			for(m=0; m<SIZE_DNA; m++)
			{
				if( rand() % 101 >= TAXA_MUTACAO )
					formigueiro[k].DNA[m] = rand() % 6;
			}
		}
		cout<< endl << " > DNA apos mutacao: ";
		for(i=0; i<SIZE_DNA; i++)
			cout<<formigueiro[k].DNA[i]<<" |";
	}

}

void rebootPopulation(PIXEL** matriz, FORMIGUEIRO* formigueiro)
{
	int k, i, j;
	int deltaI=0, deltaJ=0;
	int num_formigas, tamanho;
	int x, y;
	int m, n;
	PIXEL* formiga_remover;

	for(k=0; k<N_FORMIGUEIROS; k++)
	{
		num_formigas = formigueiro[k].fila_formigas->tamanho;
		for(tamanho = 0; tamanho<num_formigas; tamanho++)
		{
			formiga_remover = desenfileirar(formigueiro[k].fila_formigas);
			x = formiga_remover->x;
			y = formiga_remover->y;
			for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
			{
				for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
					if(  pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
						if( ( x+m >= 190 && x+m < 240 ) || ( x+m >= 600 && x+m < 650 ) || ( x+m >= 1010 && x+m < 1060 ) )
						{
							matriz[x+m][y+n].type = A;
							matriz[x+m][y+n].vida = -1;
						}
						else 
						{
							matriz[x+m][y+n].type = VAZIO;
							matriz[x+m][y+n].vida = -1;
						}
			}
		}	
	}


	for(k=0; k<N_FORMIGUEIROS; k++)
	{
		for(i=10+deltaI; i<190+deltaI; i++)
		{
			for(j=10+deltaJ; j<310+deltaJ; j++)
			{
				if( (rand() % 101 >= 80) &&
				(matriz[i+ANT_RATIO][j].type != F) && (matriz[i-ANT_RATIO][j].type != F) &&
				(matriz[i][j+ANT_RATIO].type != F) && (matriz[i][j-ANT_RATIO].type != F) &&
				(matriz[i+ANT_RATIO][j].type != P) && (matriz[i-ANT_RATIO][j].type != P) &&
				(matriz[i][j+ANT_RATIO].type != P) && (matriz[i][j-ANT_RATIO].type != P) )
				{
					matriz[i][j].vida = MAX_VIDA;
					for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
					{
						for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
						{
							if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) )
							{
								matriz[i+m][j+n].type = F;
								if(!(m==0 && n==0)) matriz[i+m][j+n].vida = -1;
							}
						}
							
					}
					enfileirar(formigueiro[k].fila_formigas, &matriz[i][j]);
				}
			}
		}
		if(k==2) 
		{
			deltaJ += 310;
			deltaI = 0;
		}
		else deltaI += 410;
	}
}




