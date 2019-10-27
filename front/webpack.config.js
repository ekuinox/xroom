module.exports = {
    mode: "development",
    entry: "./src/index.tsx",
    output: {
        path: `${__dirname}/../public/assets/scripts`,
        filename: `bundle.js`
    },
    module: {
        rules: [{
            test: /\.tsx?$/,
            exclude: /node_modules/,
            loader: 'babel-loader'
        }, {
            test: /\.css$/,
            loader: ['style-loader', 'css-loader']
        }, {
            test: /\.(jpg|png|gif|svg|pdf|ico)$/,
            use: [
                {
                    loader: 'file-loader',
                    options: {
                        name: 'assets/[hash:8].[ext]'
                    },
                },
            ],
        }]
    },
    resolve: {
        extensions: [".ts", ".tsx", ".js", ".jsx"]
    }
};
