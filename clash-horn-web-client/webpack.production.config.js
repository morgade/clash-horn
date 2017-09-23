var webpack = require('webpack');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

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
            {test: /\.png$/, loader: 'file'},
            {test: /\.gif$/, loader: 'file'},
            {test: /\.jpg$/, loader: 'file'},
            {test: /\.(woff|woff2)$/, loader: 'file'},
            {test: /\.ttf$/, loader: 'file'},
            {test: /\.otf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream'},
            {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file'},
            {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=image/svg+xml'}
        ]
    },
    
    devtool: 'cheap-module-source-map',
    
    plugins:[
        new HtmlWebpackPlugin({
          template: 'src/index-template.html',
          favicon: 'src/img/favicon.png'
        }),
        
        new webpack.DefinePlugin({
          'process.env':{
            'NODE_ENV': JSON.stringify('production')
          }
        })
    ]
};

module.exports = config;