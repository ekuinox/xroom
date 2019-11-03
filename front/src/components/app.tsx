import * as React from 'react'

interface MyWindow extends Window {
    ws: WebSocket
    foo: string
}
declare var window: MyWindow

declare const roomId: string

export default class App extends React.Component {
    ws: WebSocket

    constructor() {
        super({})
        this.ws = new WebSocket('ws://localhost:9000/ws/' + roomId)

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
