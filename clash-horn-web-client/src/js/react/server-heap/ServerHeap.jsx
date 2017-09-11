import React from 'react';

class ServerHeap extends React.Component {
    render() {
        return (
            <div className="alert alert-info">
                Free Heap: {this.props.heap.free} / {this.props.heap.max}
            </div>
        );
    }
};

ServerHeap.defaultProps = {
  heap: { free: '--', max: '--' }
};

export default ServerHeap;