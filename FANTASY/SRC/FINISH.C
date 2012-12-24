
/* Producer/Consumer with fixed size buffer */

#include <errno.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include "shared.h"
#include "location.h"
#include "movable_object.h"
#include "fan_err.h"


int main(int argc, char *argv[]) {
	int i;
	movable_object_t *the_goose, *the_gnome;
	chdir(DRAGONS_LAIR);
	if ((the_goose = read_movable_object(GOOSE)) != NULL) {
		kill(the_goose -> value, SIGINT);
		unlink(GOOSE);
	}
	if ((the_gnome = read_movable_object(GNOME)) != NULL) {
		kill(the_gnome -> value, SIGINT);
		unlink(GNOME);
	}
	return 0;
}

