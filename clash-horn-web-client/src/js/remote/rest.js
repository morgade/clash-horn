/* global fetch */

class Rest {
    post(url, body) {
        return fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        })
        .then(this.assertStatus);
    }
    
    get(url) {
        return fetch(url, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(this.assertStatus)
        .then(response => response.json());
    }
    
    delete(url) {
        return fetch(url, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(this.assertStatus);
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

module.exports = new Rest();