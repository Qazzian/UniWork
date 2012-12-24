 
/* Producer */

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

void lay_eggs(int argc, char *argv[]){
	/* arguments are shared memory id and three semaphores:
		mutex, space_available and item_available 
		These are all defined in "shared.h" as ints */

	shmid = atoi(argv[1]); /* value of shared mem handler */
	mutex = atoi(argv[2]); /* values of Semaphors */
	space_available = atoi(argv[3]);
	item_available = atoi(argv[4]);

	/* Defined in shared.h as a pointer to a buffer_t struct */
	buffer = (buffer_t *)shmat(shmid,0,0);

	semop(mutex, &wait_buf, 1);	/* P(mutex) */
	buffer -> users++;		/* register as a buffer user */
	semop(mutex, &signal_buf, 1);	/* V(mutex) */

	signal(SIGINT,exit_prog); /* register signal handler for finishing */

	for(;;) {
		semop(space_available, &wait_buf, 1);		/* P(space_available) */
		semop(mutex, &wait_buf, 1);						/* P(mutex)				 */
		buffer -> content[buffer -> back] = 1;
				/* put item in buffer */
		buffer -> size++;
		printf("The goose lays a Golden Egg in its nest \n%d eggs now in the nest.\n", 
		buffer -> size);
		(buffer -> back)++;
		if (buffer -> back == BUFFER_SIZE) buffer -> back = 0;
		/*display_buffer(buffer);													 */
		semop(mutex, &signal_buf, 1);					/* V(mutex)				 */
		semop(item_available, &signal_buf, 1);		/* V(item_available)  */
		sleep(2+rand_r(&rand_seed)%3);
	}
}


int main(int argc, char *argv[]) {
	write_movable_object(DRAGONS_LAIR,
				construct_movable_object("goose",DENIZEN,
					"a small goose which appiers to be laying golden eggs",
					5000,getpid()));
	write_movable_object(DRAGONS_LAIR,
				construct_movable_object("nest",TREASURE,
					"a small nest sitting next to the lake.\n",
					5000,0));	
	lay_eggs(argc,argv);
}

