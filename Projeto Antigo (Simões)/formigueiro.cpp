#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "formigueiro.h"

using namespace std;

FILA* criar_fila()
{
    FILA *fila = (FILA *) malloc (sizeof(FILA));

    fila->inicio=NULL;
    fila->fim=NULL;
    fila->tamanho=0;

    return fila;
}
    
void enfileirar(FILA* fila, PIXEL* formiga_nova)
{
    if(fila->inicio == NULL)
    {
        fila->inicio = formiga_nova;
        fila->fim = formiga_nova;
        formiga_nova->proximo = NULL;
        formiga_nova->anterior = NULL;
        fila->tamanho++;
    }
    else
    {
        fila->fim->proximo = formiga_nova;
        formiga_nova->anterior = fila->fim;
        fila->fim = formiga_nova;
        formiga_nova->proximo = NULL;
        fila->tamanho++;
    }
}

PIXEL* desenfileirar(FILA* fila)
{
    PIXEL* p_aux;
    if (fila->inicio == NULL);
    else if (fila->inicio == fila->fim)
    {
        p_aux=fila->inicio;
        fila->inicio = NULL;
        fila->fim = NULL;
        fila->tamanho=0;
        return p_aux;
    }
    else 
    {
        p_aux=fila->inicio;
        fila->inicio = fila->inicio->proximo;
        fila->inicio->anterior = NULL;
        fila->tamanho--;
        return p_aux;
    }
}

VISAO* antVision(PIXEL** matriz, PIXEL* formiga_analisar)
{
	int i, j;
	int norte = -1, sul = -1, leste = -1, oeste = -1;
	int contador_norte = 0, contador_sul = 0, contador_leste = 0, contador_oeste = 0;
	int x = formiga_analisar->x;
	int y = formiga_analisar->y;
	

	for(i=-VISION_RATIO; i<=VISION_RATIO; i++)
	{
		for(j=-VISION_RATIO; j<=VISION_RATIO; j++)
		{
			if( ( pow(i,2) + pow(j,2) > pow(ANT_RATIO,2) ) && ( pow(i,2) + pow(j,2) <= pow(VISION_RATIO,2) ) )
			{
				if( (j >= i) && (j >= -i) ) // Norte
				{
					switch(matriz[x+i][y+j].type)
					{
						case P:
							if(norte == -1) norte = P;
						break;
	
						case A:
							if(norte == -1) norte = A;
						break;
	
						case F:
							contador_norte++;
						break;
					}
				}

				else if( (j <= i) && (j >= -i) ) 
				{
					switch(matriz[x+i][y+j].type)
					{
						case P:
							if(leste == -1) leste = P;
						break;
	
						case A:
							if(leste == -1) leste = A;
						break;
	
						case F:
							contador_leste++;
						break;
					}
				}
	
				else if( (j <= i) && (j <= -i) ) 
				{
					switch(matriz[x+i][y+j].type)
					{
						case P:
							if(sul == -1) sul = P;
						break;
	
						case A:
							if(sul == -1) sul = A;
						break;
	
						case F:
							contador_sul++;
						break;
					}
				}
	
				else if( (j >= i) && (j <= -i) ) 
				{
					switch(matriz[x+i][y+j].type)
					{
						case P:
							if(oeste == -1) oeste = P;
						break;
	
						case A:
							if(oeste == -1) oeste = A;
						break;
	
						case F:
							contador_oeste++;
						break;
					}
				}
			}
		}
	}


	int max_contador =  (0.7 * 0.25 * M_PI) * (pow(VISION_RATIO,2) - pow(ANT_RATIO,2))  ; 

	//cout<<"max contador  "<<max_contador<<endl;
	//cout<< contador_norte <<" // " << contador_leste <<" // " << contador_sul<<" // " <<  contador_oeste<<endl;

	if(norte == -1) 
	{
		if( contador_norte >= max_contador ) norte = MAX_F;
		else norte = MIN_F;
	}

	if(oeste == -1) 
	{
		if( contador_oeste >= max_contador ) oeste = MAX_F;
		else oeste = MIN_F;
	}

	if(sul == -1) 
	{
		if( contador_sul >= max_contador ) sul = MAX_F;
		else sul = MIN_F;
	}
	
	if(leste == -1) 
	{
		if( contador_leste >= max_contador ) leste = MAX_F;
		else leste = MIN_F;
	}

	
	VISAO* visao_formiga_analisar = (VISAO*)malloc(sizeof(VISAO));
	visao_formiga_analisar->N = norte;
	visao_formiga_analisar->O = oeste;
	visao_formiga_analisar->S = sul;
	visao_formiga_analisar->L = leste;

	/*cout<<" > Campo de visao" << endl;
	cout<<" 	-> Visao Norte:  "<< visao_formiga_analisar->N <<endl;
	cout<<" 	-> Visao Oeste:  "<< visao_formiga_analisar->O <<endl;
	cout<<" 	-> Visao Sul:    "<< visao_formiga_analisar->S <<endl;
	cout<<" 	-> Visao Leste:  "<< visao_formiga_analisar->L <<endl;*/

	return visao_formiga_analisar;
}

int antDecision(FORMIGUEIRO* formigueiro, VISAO* visao_formiga)
{
	int estado;
	int Agua, MuitasFormigas, PoucasFormigas, Parede;
	int total  = 0;
	int potenc = 1;

	if( (visao_formiga->N == A) || ( visao_formiga->O == A) || (visao_formiga->S == A) || (visao_formiga->L == A) )
		Agua = 1;
	else Agua = 0;

	if( (visao_formiga->N == MAX_F) || ( visao_formiga->O == MAX_F) || (visao_formiga->S == MAX_F) || (visao_formiga->L == MAX_F) )
		MuitasFormigas = 1;
	else MuitasFormigas = 0;

	if( (visao_formiga->N == MIN_F) || ( visao_formiga->O == MIN_F) || (visao_formiga->S == MIN_F) || (visao_formiga->L == MIN_F) )
		PoucasFormigas = 1;
	else PoucasFormigas = 0;

	if( (visao_formiga->N == P) || ( visao_formiga->O == P) || (visao_formiga->S == P) || (visao_formiga->L == P) )
		Parede = 1;
	else Parede = 0;

	estado = Agua*1000 + MuitasFormigas*100 + PoucasFormigas*10 + Parede*1;

	//cout << " > Estado: " << estado;

	while(estado > 0)
	{
		total += estado% 10 * potenc;
		estado = estado / 10;
		potenc = potenc * 2;
	}

	//cout << " ( " << total <<  " )" << endl;

	//cout << " > Decisao: " << formigueiro->DNA[total] << endl;

	return formigueiro->DNA[total];
}

void antMove(PIXEL** matriz, PIXEL* formiga_mover, VISAO* visao_formiga, int Decision)
{
	//cout<<"Vida no inicio da move: " << formiga_mover->vida <<endl;
	int aux;
	int move;

	switch(Decision)
	{
		case SEGUE_AGUA:
			if( visao_formiga->L == A ) move = LESTE;
			else if( visao_formiga->O == A ) move = OESTE;
			else if( visao_formiga->N == A ) move = NORTE; 
			else if( visao_formiga->S == A ) move = SUL;
			else move = 1+ rand() % 5;

		break;

		case SEGUE_PAREDE:
			if( visao_formiga->S == P ) move = SUL;
			else if( visao_formiga->L == P ) move = LESTE;
			else if( visao_formiga->N == P ) move = NORTE;
			else if( visao_formiga->O == A ) move = OESTE; 
			else move = 1+ rand() % 5;
		break;

		case SEGUE_MUITAS_FORMIGAS:
			if( visao_formiga->N == MAX_F ) move = NORTE;
			else if( visao_formiga->L == MAX_F ) move = LESTE;
			else if( visao_formiga->O == MAX_F ) move = OESTE;
			else if( visao_formiga->S == MAX_F ) move = SUL;
			else move = 1+ rand() % 5;
		break;

		case SEGUE_POUCAS_FORMIGAS:
			if( visao_formiga->S == MIN_F ) move = SUL;
			else if( visao_formiga->L == MIN_F ) move = LESTE;
			else if( visao_formiga->O == MIN_F ) move = OESTE;
			else if( visao_formiga->N == MIN_F ) move = NORTE; 
			else move = 1+ rand() % 5;
		break;

		case FICA_PARADO:
			move = PARADO;
		break;

		case PROCURA_ALEATORIAMENTE:
			move = rand() % 5;
		break;
	}

	int x = formiga_mover->x;
	int y = formiga_mover->y;
	int vida = formiga_mover->vida;
	int m, n;

	switch(move)
	{
		case NORTE:
			if( (matriz[x][y-1-ANT_RATIO].type == P) ) move = PARADO;
		break;

		case LESTE:
			if( (matriz[x+1+ANT_RATIO][y].type == P) ) move = PARADO;
		break; 

		case SUL:
			if( (matriz[x][y+1+ANT_RATIO].type == P) ) move = PARADO; 
		break;

		case OESTE:
			if( (matriz[x-1-ANT_RATIO][y].type == P) ) move = PARADO;
		break;
	}

	for(m=-ANT_RATIO; ( move != PARADO ) && ( m<=ANT_RATIO ); m++)
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


	switch(move)
	{
		case PARADO:
		break;

		case NORTE:
			*formiga_mover = matriz[x][y-1];
			for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
			{
				for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
					if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
						matriz[x+m][y-1+n].type = F;
			}
		break;

		case LESTE:	
			*formiga_mover = matriz[x+1][y];
			for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
			{
				for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
					if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
						matriz[x+1+m][y+n].type = F;
			}	
		break;

		case SUL:
			*formiga_mover = matriz[x][y+1];
			for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
			{
				for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
					if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
						matriz[x+m][y+1+n].type = F;
			}
			break;
		break;

		case OESTE:
			*formiga_mover = matriz[x-1][y];
			for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
			{
				for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
					if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
						matriz[x-1+m][y+n].type = F;
			}
		break;
	}

	formiga_mover->type = F;
	formiga_mover->vida = vida;

	//cout<<"Vida na move: " << formiga_mover->vida <<endl;

	//cout<<" Novas coordenadas: "<< formiga_mover->x << "   "<< formiga_mover->y <<endl;
}

int antLife(PIXEL** matriz, PIXEL* formiga_analisar)
{
	int m, n;
	int total=0, agua=0, formigas=0;
	int tamanho;

	int x = formiga_analisar->x;
	int y = formiga_analisar->y;

	if( ( x >= 190 && x < 240 ) || ( x >= 600 && x < 650 ) || ( x >= 1010 && x < 1060 ) ) 
	{
		for(m=-(ANT_RATIO+1); m<=(ANT_RATIO+1); m++)
		{
			for(n=-(ANT_RATIO+1); n<=(ANT_RATIO+1); n++)
			{
				if( pow(m,2) + pow(n,2) >= pow(ANT_RATIO,2) )
				{
					total++;
					if(matriz[x+m][y+n].type==A) agua++;
					if(matriz[x+m][y+n].type==F) formigas++;
				}
			}
		}
	
		/*cout<<"Agua:   " << agua << endl;
		cout<<"Total:  " << total << endl;
		cout<<"Resultado:  "<< (float)agua/(float)total << endl;
		cout<<"Vida Antes:  " << formiga_analisar->vida <<endl;*/
		if( ( (float)agua/(float)total ) >= 0.5 ) formiga_analisar->vida = (formiga_analisar->vida) - 1 ;
		//cout<<"Vida:  " << formiga_analisar->vida <<endl;
	
		if(formiga_analisar->vida == 0)
		{
			for(m=-ANT_RATIO; m<=ANT_RATIO ; m++)
			{
				for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
				{
					if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
					{
						if( ( x+m >= 190 && x+m < 240 ) || ( x+m >= 600 && x+m < 650 ) || ( x+m >= 1010 && x+m < 1060 ) )
						{
							matriz[x+m][y+n].type = A;
							matriz[x+m][y+n].vida = -1;
						}
						else 
						{
							matriz[x+m][y+n].type = VAZIO;
							//matriz[x+m][y+n].vida = -1;
						}
					}
				}
			}
			return 0;
		}
	
		else return 1;
	}
	else return 1;
}