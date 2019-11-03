import * as React from 'react'

interface MyWindow extends Window {
    ws: WebSocket
}
declare var window: MyWindow

export default class App extends React.Component {
    ws: WebSocket

    constructor() {
        super({})
        this.ws = new WebSocket('ws://localhost:9000/ws/' + 100)

        this.ws.addEventListener('open', () => {
            console.log('connected!')
        })

        this.ws.addEventListener('message', msg => {
            console.log(JSON.parse(msg.data))
        })

        window.ws = this.ws;
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
