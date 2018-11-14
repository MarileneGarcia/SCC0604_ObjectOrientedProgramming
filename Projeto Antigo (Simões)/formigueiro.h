#ifndef FORMIGUEIRO_H

    #define FORMIGUEIRO_H
    
    #define SIZE_DNA 16

    #define LN_SCREEN 1240		// Número de pixeis de comprimento da tela
    #define H_SCREEN 630		// Número de pixeis de altura da tela
    
    // Objetos do cenário
    #define F 0					// Formiga
    #define A 1					// Água
    #define P 2					// Parede
    #define VAZIO 3					// Vazio
    #define MAX_F 4
    #define MIN_F 5
    
    // Possiveis decisões
    #define SEGUE_AGUA 0
    #define SEGUE_PAREDE 1
    #define SEGUE_MUITAS_FORMIGAS 2
    #define SEGUE_POUCAS_FORMIGAS 3
    #define FICA_PARADO 4
    #define PROCURA_ALEATORIAMENTE 5

    // Possiveis ações
    #define PARADO 0
    #define NORTE 1				// Norte
    #define OESTE 2				// Oeste
    #define SUL 3				// Sul
    #define LESTE 4				// Leste
    
    #define ANT_RATIO 1
    #define VISION_RATIO 4
    #define N_FORMIGUEIROS 6
    #define TAXA_MUTACAO 40
    #define MAX_VIDA 4
    #define LOOP_SIZE 100

    typedef struct pixel PIXEL;
    struct pixel
    {
        int type;
        int x;
        int y;
        int vida;
        PIXEL* anterior;
        PIXEL* proximo;
    };

    struct fila
    {
        PIXEL* inicio;
        PIXEL* fim;
        int tamanho;
    };
    typedef struct fila FILA;
    
    struct formigueiro
    {
        int indice;
        int DNA[SIZE_DNA];
        FILA* fila_formigas;
        float x_medio;
        int x_max;
        float fitness;
    };
    typedef struct formigueiro FORMIGUEIRO;

    struct visao
    {
        int N;
        int O;
        int S;
        int L;
    };
    typedef struct visao VISAO;

    FILA* criar_fila();

    void enfileirar(FILA*, PIXEL*);

    PIXEL* desenfileirar(FILA*);

    VISAO* antVision(PIXEL**, PIXEL*);
    
    int antDecision(FORMIGUEIRO*, VISAO*);

    void antMove(PIXEL**, PIXEL*, VISAO*, int);

    int antLife(PIXEL**, PIXEL*);

#endif
