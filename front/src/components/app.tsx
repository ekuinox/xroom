import * as React from 'react'

interface MyWindow extends Window {
    ws: WebSocket
    foo: string
}
declare var window: MyWindow

declare const roomId: string

interface State {
    text: string
}

export default class App extends React.Component<{}, State> {
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

        window.ws = this.ws

        this.setState({text: ""})
    }

    send(message: any) {
        this.ws.send(JSON.stringify(message))
    }

    render() {
        return (
            <div style={{color: "yellow"}}>
                Hello World
                <textarea rows={1} cols={30} onChange={event => this.setState({text: event.target.value})}/>
                <button onClick={() => {
                    this.send({eventType: "Talk", username: "noname", text: this.state.text})
                }}>はい</button>

            </div>
        )
    }
}
