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

type EventType = 'Join' | 'Leave' | 'Talk' | 'Draw' | 'UpdatePen'
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

interface UpdatePen extends Event<'UpdatePen'> {
    username: string,
    pen: {
        color: string
    }
}

interface Position {
    x: number,
    y: number
}

interface Palette {
    position: Position,
    color: string
}

interface Draw extends Event<'Draw'> {
    username: string,
    position: Position
}

export default class App extends React.Component<{}, State> {
    ws: WebSocket
    isDraw: boolean
    lastPositions: { [key: string]: Palette; } = {}

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

                this.lastPositions[join.username] = {
                    position: { x: null, y: null },
                    color: "#000000"
                }
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

            if (data.eventType == 'Draw') {
                const draw = data as Draw

                const context = (document.getElementById('mainCanvas') as HTMLCanvasElement).getContext('2d')

                const palette = this.lastPositions[draw.username]
                const lastPosition = palette.position

                context.lineCap = 'round';
                context.lineJoin = 'round';
                context.lineWidth = 5;
                context.strokeStyle = palette.color;

                const { x, y } = draw.position

                if (lastPosition.x === null || lastPosition.y === null) {
                    context.moveTo(x, y);
                } else {
                    context.moveTo(lastPosition.x, lastPosition.y);
                }
                context.lineTo(x, y);
                context.stroke();

                this.lastPositions[draw.username].position = {x, y};

                return
            }
        })

        window.ws = this.ws

        this.isDraw = false
        this.lastPositions = {}

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

                <canvas
                    id="mainCanvas"
                    onMouseDown={() => this.isDraw = true }
                    onMouseOut={() => this.isDraw = false }
                    onMouseUp={() => this.isDraw = false }
                    onMouseMove={event => {
                        if (!this.isDraw) return

                        this.send({
                            eventType: 'Draw',
                            position: {
                                x: event.clientX,
                                y: event.clientY
                            }
                        })
                    }}
                    style={{
                        border: 'solid'
                    }}
                >

                </canvas>

            </div>
        )
    }
}
