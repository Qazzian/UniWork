
/* Producer/Consumer with fixed size buffer */

#include <errno.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

#include <sys/ipc.h>
#include <sys/sem.h>
#include "location.h"
#include "movable_object.h"
#include "fan_err.h"
#include "shared.h"

int main(void) {

  /* to pass arguments to producer and consumer processes */
  char shmid_arg[12], mutex_arg[12], 
       space_available_arg[12], item_available_arg[12];

	if(fork() != 0) {
		exit(0);
	}
/*	chdir(get_current_location(PLAYERS_LOCATION));*/
  /* allocate buffer in shared memory */
  shmid = shmget(0, sizeof(buffer_t), 0666 | IPC_CREAT);
  sprintf(shmid_arg,"%d",shmid);
  buffer = (buffer_t *)shmat(shmid, 0, 0);
  buffer -> front = 0;
  buffer -> back = 0;
  buffer -> users = 0;

  /* allocate mutex and initialise to 1 */
  mutex = semget(SEMKEY, 1, SEMPERM | IPC_CREAT);
  sprintf(mutex_arg,"%d",mutex);
  semctl_arg.val = 1;
  semctl(mutex, 0, SETVAL, semctl_arg);

  /* allocate space_available and initialise to BUFFER_SIZE */
  space_available = semget(SEMKEY, 1, SEMPERM | IPC_CREAT);
  sprintf(space_available_arg,"%d",space_available);
  semctl_arg.val = BUFFER_SIZE;
  semctl(space_available, 0, SETVAL, semctl_arg);

  /* allocate item_available and initialise to 0 */
  item_available = semget(SEMKEY, 1, SEMPERM | IPC_CREAT);
  sprintf(item_available_arg,"%d",item_available);
  semctl_arg.val = 0;
  semctl(item_available, 0, SETVAL, semctl_arg);

  /* fork the producer */
  if (fork() == 0) {
    setpgrp();
    execlp("./goose", "goose", shmid_arg, 
      mutex_arg, space_available_arg, item_available_arg, NULL);
  }

  /* and the consumer */
  if (fork() == 0) {
    setpgrp();
    execlp("./gnome", "gnome", shmid_arg, 
      mutex_arg, space_available_arg, item_available_arg, NULL);
  }
  
  return 0;
}
