/**
 * サーバとやりとりするWebSocketでのメッセージを定義する
 * Incoming/Outgoingをわけるとかしたい
 * 
 */
import { Position, Palette } from './types';

/**
 * メッセージを定義する
 */
export type CreateMessage<Name extends string, T = unknown> = {
  type: Name;
  data: T;
};

export type JoinMessage = CreateMessage<'Join', {
  username: string;
}>;

export type LeaveMessage = CreateMessage<'Leave', {
  username: string;
}>;

export type ChatMessage = CreateMessage<'Chat', {
  username: string;
  text: string;
}>;

export type UpdatePenMessage = CreateMessage<'UpdatePen', {
  username: string;
  pen: {
    color: string;
  };
}>;

export type DrawMessage = CreateMessage<'Draw', {
  username: string;
  position: Position;
}>;

export type Message = 
  JoinMessage |
  LeaveMessage |
  UpdatePenMessage |
  DrawMessage |
  ChatMessage;

export const isMessage = (message: any): message is Message => typeof message.type === 'string';
