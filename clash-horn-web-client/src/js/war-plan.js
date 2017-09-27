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
 * Finds member positions elligible for pushing
 * Members with 2 performedAttacks or already in the positions's attack queue are removed
 * @param {object} war
 * @param {object} position
 * @returns {object}
 */
export const elligilePositionsForPushToQueue = function(war, position) {
    const count = performedMemberAttacksCountPerPosition(war);
    return war.positions.filter( 
            p => {
                return (count[p.member.mapPosition]||0)<2 && !position.attackQueue.find( a => a.attacker === p.number ) ;
            } 
    );
};

/**
 * Finds best performed attack at the position
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


export const performedMemberAttacksCountPerPosition = function(war) {
    let counts = [];
    war.positions.map( 
        pos => pos.performedAttacks.map( attack => attack.attacker) 
    )
    .reduce( (x, y) => x.concat(y) )
    .forEach( a => counts[a] = (counts[a]||0) + 1 );
    
    return counts;
}

/**
 * Return the position attackQueue filtering attackers into position.performedAttacks
 * Removes attackers with 2 performedAttacks or attackers already present in position's performedAttacks
 * @param {type} war
 * @param {type} position
 * @returns {unresolved}
 */
export const getFilteredAttackQueue = function(war, position) {
    const count = performedMemberAttacksCountPerPosition(war);
    
    return position.attackQueue
            // filter out attackers lready in the queue
            .filter( queueItem => position.performedAttacks.map(a=> a.attacker).indexOf(queueItem.attacker) < 0 )
            // filter out atackers with 2 or more performed attacks
            .filter( queueItem => (count[queueItem.attacker] || 0) < 2 );
}


export const bestScoredPerformedAttackAgainst = function(war, position) {
    let p =  war.positions
        // Positions to performed attack list against position
        .map( 
            p => p.performedAttacks.filter( a => a.defender===position.number ) 
        )
        .reduce( (x, y) => x.concat(y) );

    if (p.length>0) {
        // reduced to max stars/destruction attack
        return p.reduce( (x, y) => (x.stars*1000+x.destructionPercentage) > (y.stars*1000+x.destructionPercentage) ? x : y);
    }
    
    return { stars: -1, destructionPercentage: 0 };
}