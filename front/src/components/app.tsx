import * as React from 'react'
import { isMessage, Message } from '../common/messages';
import { Palette } from '../common/types';

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
    isDraw: boolean
    lastPositions: { [key: string]: Palette; } = {}

    constructor() {
        super({});
        this.ws = new WebSocket('ws://localhost:9000/ws/' + roomId);

        this.ws.addEventListener('open', () => {
            console.log('connected!')
        });

        this.ws.addEventListener('message', msg => {
            const data = JSON.parse(msg.data);
            if (isMessage(data)) {
                this.onMessage(data);
                return;
            }
        });

        window.ws = this.ws;

        this.isDraw = false;
        this.lastPositions = {};

        this.setState({text: ""});
    }

    send(message: any) {
        this.ws.send(JSON.stringify(message));
    }

    onMessage(message: Message) {
        if (message.type === 'Join') {
            console.log(`${message.data.username}: joined`)

            this.lastPositions[message.data.username] = {
                position: { x: null, y: null },
                color: "#000000"
            }
            return;
        }

        if (message.type === 'Leave') {
            console.log(`${message.data.username}: left`)
            return
        }

        if (message.type === 'Chat') {
            console.log(`${message.data.username}: ${message.data.text}`)
            return
        }

        if (message.type === 'Draw') {

            const context = (document.getElementById('mainCanvas') as HTMLCanvasElement).getContext('2d')

            const palette = this.lastPositions[message.data.username]
            const lastPosition = palette.position

            context.lineCap = 'round';
            context.lineJoin = 'round';
            context.lineWidth = 5;
            context.strokeStyle = palette.color;

            const { x, y } = message.data.position

            if (lastPosition.x === null || lastPosition.y === null) {
                context.moveTo(x, y);
            } else {
                context.moveTo(lastPosition.x, lastPosition.y);
            }
            context.lineTo(x, y);
            context.stroke();

            this.lastPositions[message.data.username].position = {x, y};

            return;
        }
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
