import React from 'react';
import { connect } from 'react-redux';
import Alert from 'react-bootstrap/lib/Alert';

class CoCStatusAlert extends React.Component {
    
    render() {
        var alert = null;
        if (this.props.cocApiLatestError) {
            
            if (this.props.method && !this.props.method===this.props.cocApiLatestError.method) {
                return null;
            }
            
            var m1 = null;
            var m2 = null;
            var m3 = (<small>CoC Service Response: {this.props.cocApiLatestError.message}{' '}{this.props.cocApiLatestError.reason}</small>);
            
            switch (this.props.cocApiLatestError.status) {
                case 404:
                    m2 = this.props.notFound || "Could not locate your requested data";
                    m3 = null;
                    break;
                case 403:
                    m1 = "Access Denied";
                    m2 = this.props.denied || "Cannot access requested data";
                    break;
                case 429:
                    m1 = "Throttled";
                    m2 = this.props.trothled || "Clash of Clans data service is unavailable at the moment";
                    break;
                case 500:
                    m1 = "Clash Service Error";
                    m2 = this.props.internalError || "Clash of Clans data service responded with an error status";
                    break;
                case 503:
                    m1 = "Mainentance";
                    m2 = this.props.internalError || "Clash of Clans data service is temprorarily unavailable because of maintenance";
                    break;
                default:
                    m1 = "Unknown error: "+this.props.cocApiLatestError.reason;
                    m2 = this.props.cocApiLatestError.reason;
                    break;
            }
            
            alert = (
                <Alert bsStyle={this.props.bsStyle||'danger'}>
                    <strong>{m1}</strong> 
                    {' '}
                    <p>{m2}</p>
                    {' '}
                    {m3}
                </Alert>
            );
        }
        
        return alert;
    }
};

export default connect(
    store => ({ 
        cocApiLatestError: store.clans.cocApiLatestError
    })
)(CoCStatusAlert);