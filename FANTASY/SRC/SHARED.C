
/* Producer/Consumer - shared operations */

#include <errno.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

#include <sys/ipc.h>
#include <sys/sem.h>

#include "shared.h"
#include "movable_object.h"
#include "location.h"
#include "fan_err.h"

/* show contents of non-empty buffer */
void display_buffer(buffer_t *buffer) {
  printf("The nest contains %d eggs", buffer -> size);
}

/* last user releases semaphores and shared memory before exiting */
void exit_prog(int signo) {
  semop(mutex, &wait_buf, 1);	/* P(mutex) */
  if (buffer -> users > 1) {	/* not the last user */
    buffer -> users--;		/* de-register as a buffer user */
    semop(mutex, &signal_buf, 1); /* V(mutex) */
  }
  else { /* last user tidies up */
    semctl(mutex, 0, IPC_RMID);
    semctl(space_available, 0, IPC_RMID);
    semctl(item_available, 0, IPC_RMID);
    shmctl(shmid, IPC_RMID, NULL);
  }
  exit(0);
}

