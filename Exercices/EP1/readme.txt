/******************************************************************************
 *  Name:  Eduardo Rocha Laurentino  
 *  NUSP: 8988212  
 *  Precept: 
 *
 *  Partner Name:     
 *  Partner NetID:    
 *  Partner Precept:  
 *
 *  Hours to complete assignment (optional): +- 3h/dia durante 3 dias
 *
 ******************************************************************************/

Programming Assignment 2: Deques and Randomized Queues


/******************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 *****************************************************************************/

	Meu deque foi implementado com lista duplamente ligada. Eu basicamente me basiei na
estutura de uma fila/pilha implementado com LL e o fiz duplamente ligado por conta da característica didirecional.
	Minha randomized queue foi implementado com vetor, cujo tamanho é contantemente alterado de acordo
com a necessidade. 

/******************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 *****************************************************************************/

Randomized Queue:   ~  _____  bytes

Deque:              ~  _____  bytes




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
	
	Bruna Thalenberg me ajudou a pensar em como eu poderia reorganizar
o vetor da randomized queue depois de retirar um elemento aleatório, para
nao deixar "buracos null" no meio dele. 


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

	Gastei muito tempo tentando implementar o iterador da Randomized Queue 
pensando em a cada next() "sortear" o elemento; por fim, ao perceber que e enunciado
diz que posso e preciso usar espaço extra linear, trabalhei com um vetor auxiliar
o qual eu aleatorizo de uma vez, o que também permite criar iteradores independentes. 


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
