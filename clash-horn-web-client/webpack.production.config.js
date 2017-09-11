var webpack = require('webpack');
var path = require('path');

var BUILD_DIR = path.resolve(__dirname, 'public');
var APP_DIR = path.resolve(__dirname, 'src');

var config = {
    entry: ['es6-promise', 'fetch-ie8', APP_DIR + '/js/index.jsx'],
    output: {
        path: BUILD_DIR,
        filename: 'bundle.js'
    },

    module: {
        loaders: [
            {
                test: /\.jsx?/,
                include: APP_DIR,
                loader: 'babel'
            },
            {test: /\.less$/,loader: "style!css!less"},
            {test: /\.css$/, loader: "style-loader!css-loader"},
            {test: /\.png$/, loader: "url-loader?limit=100000"},
            {test: /\.jpg$/, loader: "file-loader"},
            {test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/font-woff'},
            {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream'},
            {test: /\.otf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream'},
            {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file'},
            {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=image/svg+xml'}
        ]
    },
    
    devtool: 'cheap-module-source-map',
    
    plugins:[
        new webpack.DefinePlugin({
          'process.env':{
            'NODE_ENV': JSON.stringify('production')
          }
        })
    ]
};

module.exports = config;