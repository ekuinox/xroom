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

export type BroadcastJoinMessage = CreateMessage<'BroadcastJoin', {
  username: string;
}>;

export type BroadcastLeaveMessage = CreateMessage<'BroadcastLeave', {
  username: string;
}>;

export type BroadcastChatMessage = CreateMessage<'BroadcastChat', {
  username: string;
  text: string;
}>;

export type BroadcastUpdatePenMessage = CreateMessage<'BroadcastUpdatePen', {
  username: string;
  pen: {
    color: string;
  };
}>;

export type BroadcastErrorMessage = CreateMessage<'BroadcastError', {
  message: string;
}>;

export type BroadcastDrawMessage = CreateMessage<'BroadcastDraw', {
  username: string;
  position: Position;
}>;

export type RequestChatMessage = CreateMessage<'RequestChat', {
  text: string;
}>;

export type RequestDrawMessage = CreateMessage<'RequestDraw', {
  position: Position;
}>;

export type ResponseMessage = CreateMessage<'Response', {
  ok: boolean
}>;

export type Message = 
  BroadcastJoinMessage |
  BroadcastLeaveMessage |
  BroadcastUpdatePenMessage |
  BroadcastDrawMessage |
  BroadcastChatMessage |
  BroadcastErrorMessage |
  RequestChatMessage |
  RequestDrawMessage |
  ResponseMessage;

export const isMessage = (message: any): message is Message => typeof message.type === 'string';
