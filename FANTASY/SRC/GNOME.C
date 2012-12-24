
/* Consumer */

#include <errno.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include "movable_object.h"
#include "location.h"
#include "fan_err.h"
#include "shared.h"

int main(int argc, char *argv[]) {
	write_movable_object(DRAGONS_LAIR,
			construct_movable_object("gnome",DENIZEN,
				"A gnome with a little cart. It seems that his heading for the goose's nest",
					5000,getpid()));	
  /* arguments are shared memory handle and three semaphores:
		 mutex, space_available and item_available */

  shmid = atoi(argv[1]);
  buffer = (buffer_t *)shmat(shmid,0,0);
  mutex = atoi(argv[2]);
  space_available = atoi(argv[3]);
  item_available = atoi(argv[4]);

  semop(mutex, &wait_buf, 1);	/* P(mutex) */
  buffer -> users++;		/* register as a buffer user */
  semop(mutex, &signal_buf, 1);	/* V(mutex) */

  signal(SIGINT,exit_prog); /* register signal handler for finishing */

	for(;;) {
		semop(item_available, &wait_buf, 1);			/* P(item_available)	*/
		semop(mutex, &wait_buf, 1);						/* P(mutex)				*/
		buffer->size--;
		printf("The Gnome takes a Golden egg from the nest \n %d Eggs left\n",
						 buffer -> size);
				/*consume item & decrement size at the same time */
		buffer -> front++; 
		if (buffer -> front == BUFFER_SIZE) buffer -> front = 0;
			if (!(semctl(item_available, 0, GETVAL)))
				printf("The nest is empty\n\n");
	  
	  semop(mutex, &signal_buf, 1);					/* V(mutex)				*/
	  semop(space_available, &signal_buf, 1);		/* V(space_available	*/
	  sleep(3+rand_r(&rand_seed)%3);
  }
}

