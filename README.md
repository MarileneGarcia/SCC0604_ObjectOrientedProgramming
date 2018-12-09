# Trabalho_POO   
   ## Alunos - Números USP:
   </p> Leandro Antonio Silva - 9805341
   </p> Maria Luisa do Nascimento da Silva - 10310721
   </p> Marilene Andrade Garcia - 10276974
   </p> Paulo Henrique Sebastião de Moura - 10310471 </p> </p>
   
   ## Algoritmo Evolutivo
   
   </p>Nosso trabalho de programação orientada a objetos é um algoritmo evolutivo no qual são setados 4 objetos Formigueiro,
dentro desses objetos são inicializados os objetos Formiga, e na interface gráfica GUI são inicializados 4 cenários em
quatro telas diferentes da janela, cada um contém um rio (pixels azuis) que dividem cada tela em duas partes de território
(pixels brancos), no território a esquerda há um dos formigueiros que contém formigas (pixels vermelhos) e o objetivo da
evolução são as formigas aprenderem como atravessar o rio e chegar ao território na direita da tela. Sabe-se que formigas
sozinhas não sabem nadar, logo morrem se tentarem atravessar o rio, mas formigas juntas podem atravessar formando uma ponte
com uma delas sempre na margem esquerda do rio.</p>
    </p>O algoritmo está rodando 50 gerações de formigas, sendo que em cada geração ocorrem 350 vezes os processos de decisão para
onde cada formiga irá se mover e posteriormente movimento delas. Ao final de cada geração, ocorre a evolução, ou seja os processos
de seleção do DNA do melhor formigueiro (o DNA do melhor formigueiro é o que apresenta a melhor avaliação segundo um sistema de
pontos para decidir quais formigas de cada formigueiro obtiveram o melhor desempenho em tentar atravessar o rio), cruzamento do
melhor formigueiro com os outros e mutação no DNA de um formigueiro para garantir uma aleatoriedade no processo, além da
reinicialização da população para os lugares de origem das formigas.</p>
    </p>Ao final da execução de cada geração são impressos o número de formigas de cada formigueiro, o quanto no eixo x da tela em média as formigas andaram (sendo que quanto maior o x médio, mais formigas andaram mais na tela, pois o canto totalmente à esquerda da tela é o x = 0 e o canto totalmente a direita é o x = 530, e o rio está no meio do cenário), o x da formiga que mais chegou perto do território a direita do rio também é impresso, a quantidade de formigas que entraram no rio na geração, e qual foi a avaliação das decisões das formigas (fitness).</p>
    </p>Obs. É necessário esperar muitas gerações para poder observar avanços na tarefa de atravessar o rio, pois tal qual a evolução natural, pode ser um processo demorado, e como leva em consideração a aleatoriedade, que pode ajudar o algoritmo a não se manter apenas em um ótimo local, mas pode levá-lo para DNAs ruins atrasando a evolução. Com a modificação da quantidade de gerações e o número de vezes que os processos de decisão e movimento das formigas ocorrem, é possível observar diferentes resultados.</p>


