/**
 *    WAR PLAN FULL DATA STRUCTURE (BASED ON WarPlanFullDTO)
 *    
 *   { 
 *      id: <number>,
 *      size: <number>,
 *      startTime: <date>,
 *      preparationStartTime: <date>,
 *      endTime: <date>,
 *      mapSize: <number>,
 *      clan: {
 *          tag: <string>,
 *          name: <string>
 *      }
 *      enemy: {
 *          tag: <string>,
 *          name: <string>
 *      },
 *      clanScore: {
 *          stars: <number>,
 *          destruction: <number>
 *      },
 *      enemyScore: {
 *          stars: <number>,
 *          destruction: <number>
 *      },
 *      positions: [ 
 *          {
 *              number: <number>,
 *              member: {
 *                  tag: <string>,
 *                  name: <string>,
 *                  mapPosition: <number>,
 *                  townhallLevel: <number>
 *              },
 *              enemy: {
 *                  tag: <string>,
 *                  name: <string>,
 *                  mapPosition: <number>,
 *                  townhallLevel: <number>
 *              },
 *              performedAttacks: [
 *                  {
 *                      attacker: <number>,
 *                      defender: <number>,
 *                      stars: <number>,
 *                      order: <number>,
 *                      destructionPercentage: <number>,
 *                  }
 *              ],
 *              sufferedAttacks: [
 *                  {
 *                      attacker: <number>,
 *                      defender: <number>,
 *                      stars: <number>,
 *                      order: <number>,
 *                      destructionPercentage: <number>,
 *                  }
 *              ],
 *              attackQueue: [
    *              {
    *                  attacker: <number>,
    *                  order: <number>
 *                  }
 *              ]
 *        }
 *    }
 *
 */

/**
 * Finds positions elligible for pushing
 * @param {object} war
 * @param {object} position
 * @returns {object}
 */
export const elligilePositionsForPushToQueue = function(war, position) {
    return war.positions.filter( p => !position.attackQueue.find( a => a.attacker === p.number ) );
};

/**
 * 
 * @param {type} position
 * @returns {Number}
 */
export const bestPerformedAttack = function(position) {
    if (position.performedAttacks.length===0) {
        return 0;
    } else {
        let maxStars = position.performedAttacks
                                    .map( a => a.stars)
                                    .reduce( (o,n) => Math.max(o, n) );

        return position.performedAttacks
                                .filter( a => a.stars === maxStars )
                                .map( a => a.destructionPercentage)
                                .reduce( (o,n) => Math.max(o, n));
    }
};