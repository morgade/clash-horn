/* global jest */
/* global expect */

import React from 'react';
import {shallow, mount} from 'enzyme';

import * as Actions from '../../src/js/flux/actions/routes';
import { CommentLine } from '../../src/js/react/comment-crud/CommentLine.jsx';


describe('CommentLine component', function () {
    
    it('should render props as expected', () => {
        const comment = shallow(
          <CommentLine name="Marcelo" email="morgade@gmail.com" text="This is my comment" />
        )
        .shallow();

        expect(comment.type()).toEqual("tr");
        expect(comment.find("td").length).toEqual(3);
        expect(comment.childAt(0).children().text()).toEqual('Marcelo');
        expect(comment.childAt(1).children().text()).toEqual('morgade@gmail.com');
        expect(comment.childAt(2).children().text()).toEqual('This is my comment');
    });
    
    it('should change to specific route when clicked', () => {
        const props = {
            id: "123",
            name: "Marcelo",
            email: "morgade@gmail.com",
            text: "This is my comment",
            dispatch: jest.fn()
        };
        
        Actions.routeChange = jest.fn(x=>x);
        
        const comment = shallow(<CommentLine {...props} />);
        comment.simulate('click');
        
        expect(props.dispatch).toBeCalledWith('/comment-crud/123');
        expect(Actions.routeChange).toBeCalledWith('/comment-crud/123');
    });

});