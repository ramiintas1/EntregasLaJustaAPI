import { Pedido } from "./pedido";

export class Entrega{ 

    public PED?:Pedido;

    constructor(
        public id:number,
        public zona:string,
        public dniRepartidor:string,
        public estado:string,
        public fecha:string,
        public motivo:string,
        public pedido:number,

    ){}
}