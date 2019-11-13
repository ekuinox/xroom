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

type EventType = 'Join' | 'Leave' | 'Talk'
interface Event<T extends EventType> {
    eventType: T
}

interface Join extends Event<'Join'> {
    username: string
}

interface Leave extends Event<'Leave'> {
    username: string
}

interface Talk extends Event<'Talk'> {
    text: string,
    username: string
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
            const data = JSON.parse(msg.data) as Event<EventType>
            console.log(data)

            if (data.eventType == 'Join') {
                const join = data as Join
                console.log(`${join.username}: joined`)
                return
            }

            if (data.eventType == 'Leave') {
                const leave = data as Leave
                console.log(`${leave.username}: left`)
                return
            }

            if (data.eventType == 'Talk') {
                const talk = data as Talk
                console.log(`${talk.username}: ${talk.text}`)
                return
            }

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
                    this.send({eventType: "Talk", text: this.state.text})
                }}>はい</button>

            </div>
        )
    }
}
