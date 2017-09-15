import React from 'react';
import FormControl from 'react-bootstrap/lib/FormControl';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import Button from 'react-bootstrap/lib/Button';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';

import TagFormControl from './TagFormControl.jsx';
import { fetchClanData } from '../../flux/actions/clans'

class ClanSearchInput extends React.Component {
    
    constructor(props){
        super(props);
        this.state = {
            tag: props.clan ? props.clan.tag : "#"
        };
    }
    
    findClan() {
        this.props.onFindClanRequested(this.state.tag);
    }
    
    render() {
        return (
            <InputGroup className={ this.props.clan ? "has-success" : "" }>
    
                <TagFormControl tag={this.state.tag} onTagChange={(tag) => this.setState({tag: tag})} />

                <InputGroup.Button>
                    <Button
                        type="button" 
                        bsStyle={ this.props.clan ? "success" : "primary" }
                        onClick={this.findClan.bind(this)}>
                            {this.props.clan ? 
                                <Glyphicon glyph="check" />
                                : 
                                "Locate Clan"
                            }
                    </Button>
                </InputGroup.Button>
                
            </InputGroup>
        );
    }
};

ClanSearchInput.defaultProps = {
    clan: null,
    onFindClanRequested: () => {}
}

export default ClanSearchInput;