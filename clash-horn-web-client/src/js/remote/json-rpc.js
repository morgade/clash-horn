/* global fetch */

class JsonRpc {
    
    constructor() {
        this.id = 1;
    }
    
    call(url, method, params) {
        return fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: (this.id++).toString(),
                jsonrpc: "2.0",
                method: method,
                params: params || []
            })
        })
        .then(this.assertStatus)
        .then(response => response.json() )
        .then(responseData => { 
            if (responseData.error) {
                let error = new Error(`${responseData.error.message}`);
                error.data = responseData.error.data;
                error.code = responseData.error.code;
                throw error;
            } else {
                return responseData.result;
            }
        });
    }
   
    assertStatus(response) {
        if (response.status >= 200 && response.status < 300) {
            return response;
        } else {
            var error = new Error(`Remote access error (${response.status}) ${response.statusText}`);
            error.response = response;
            throw error;
        }
    }
}

module.exports = new JsonRpc();