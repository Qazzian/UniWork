
/* Producer/Consumer - shared declarations and definitions */

#define SEMKEY 0
#define SEMPERM 0666
#define BUFFER_SIZE 6

int shmid;

typedef struct {
	int front, back;	/* items are placed at back of buffer,
								and consumed from front */
	int content[BUFFER_SIZE]; 
	int users;		/* number of processes using the buffer,
							last to exit tidies up */
	int size;	
} buffer_t;

void display_buffer(buffer_t *);

/* seed for random number generator */
static int rand_seed = 5;

/* all programs declare shared memory handle, buffer,
	and three semaphores */
int shmid;
buffer_t *buffer;
int mutex, item_available, space_available;

/* arguments to semctl; represent P and V operations */
static struct sembuf wait_buf = {0,-1,SEM_UNDO};
static struct sembuf signal_buf = {0,1,SEM_UNDO};

/* argument to semctl, used for initialising semaphores */
union semun { 
	int val;
	struct semid_ds *buf;
	ushort *array;
} semctl_arg;

void exit_prog(int signo);
