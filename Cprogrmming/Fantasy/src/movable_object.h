/* movable_object.h */

#define MAX_MOVABLE_OBJECT 1060
#define MAX_DESCRIPTION 1024
#define DENIZEN "denizen"
#define WEAPON "weapon"
#define TREASURE "treasure"
#define LIGHT_SOURCE "light source"

#define MAX_THINGS_HELD 5
#define MAX_WEIGHT_HELD 50

typedef struct {
    char *name;
    char *class; /* treasure, denizen, weapon */
    char *description;
    int weight;
    int value; /* value if treasure, pid if denizen, signal if weapon */
    } movable_object_t;

/* read_movable_object returns the structure representing the object read,
   or NULL if there is no such object in the current location */
extern movable_object_t *read_movable_object(char *);

extern void display_movable_object(movable_object_t *);

extern movable_object_t *construct_movable_object
        (char *, char *, char *, int, int);

extern int write_movable_object(char *, movable_object_t *);

