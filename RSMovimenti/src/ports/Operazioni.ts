import { Movimento } from "../models/Movimento";


export interface Operazioni{
    visualizzaMovimenti(codiceConto:string):Promise<Movimento[]>;
    versa(codiceConto:string,importo:number):Promise<void>;
    preleva(codiceConto:string,importo:number):Promise<void>;
}