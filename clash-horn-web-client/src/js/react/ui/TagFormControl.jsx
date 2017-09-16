import React from 'react';
import FormControl from 'react-bootstrap/lib/FormControl';

class TagFormControl extends React.Component {
    
    inputValueChanged(evt) {
        var tag = evt.target.value.toUpperCase().replace(/#*/g,"");
        tag = "#" + tag;
        
        if (this.props.onTagChange) {
            this.props.onTagChange(tag);
        }
    }
    
    render() {
        return (
            <FormControl type="text" 
                    disabled={this.props.disabled}
                    value={this.props.tag||''} 
                    placeholder={this.props.placeholder} 
                    onChange={this.inputValueChanged.bind(this)} />
        );
    }
};

export default TagFormControl;