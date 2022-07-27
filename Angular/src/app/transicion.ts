export class TransicionEstado{

    constructor(
        public id:number,
        public actual:string,
        public fecha:string,
        public motivo:string,
        public entrega:number
    ){}
}