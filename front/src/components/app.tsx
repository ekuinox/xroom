import * as React from 'react'

export default class App extends React.Component {
    ws: WebSocket

    constructor() {
        super({})
        this.ws = new WebSocket('ws://localhost:9000/socket')

        this.ws.addEventListener('open', () => {
            console.log('connected!')
        })

        this.ws.addEventListener('message', msg => {
            console.log(JSON.parse(msg.data))
        })
    }

    render() {
        return (
            <div style={{color: "yellow"}}>
                Hello World
                <button onClick={() => {
                    this.ws.send(JSON.stringify({"data": "foo"}))
                }}>はい</button>

            </div>
        )
    }
}
