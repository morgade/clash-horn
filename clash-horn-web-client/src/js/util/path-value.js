/**
 * Get a value of an object path, in a null/undefined safe mode
 * @param {type} root
 * @returns
 */
const pathValue = function() {
    let obj = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        if (!obj || !obj.hasOwnProperty(arguments[i])) {
          return null;
        }
        obj = obj[arguments[i]];
    }
    return obj;
};

export default pathValue;