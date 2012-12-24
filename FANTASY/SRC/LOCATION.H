/* location.h */

#define FANTASY_DIR "/mntbe/ugrad7/base/i/ifw9/Cprogs/fantasy"

#define MAX_LOCATION_LENGTH 512
#define FANTASY_ETC FANTASY_DIR "/etc"
#define STARTING_LOCATION FANTASY_DIR "/world"
#define DRAGONS_LAIR FANTASY_DIR "/world/cavern"
#define PLAYERS_LOCATION FANTASY_DIR "/tmp/player/current_location"
#define PLAYERS_STUFF FANTASY_DIR "/tmp/player/holding"
#define DRAGONS_LOCATION FANTASY_DIR "/tmp/dragon/current_location"
#define DRAGONS_STUFF FANTASY_DIR "/tmp/dragon/holding"

#define DESCRIPTION "description"
#define LIGHT "light"
#define MAX_THINGS_AT_LOC 12
#define MAX_EXITS_FROM_LOC 6
#define MAX_NAME 25

extern char *get_current_location(char *location_path);

extern int set_current_location(char *location_path,char *new_location);

/* any_exit returns one of the exits from from_location, or if there
   is no location, it returs from_location itself */
extern char *any_exit(char *from_location);

