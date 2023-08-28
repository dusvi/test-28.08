import express from "express";
import { Movimento } from "../models/Movimento";
import { MongoAdapter } from "./MongoAdapter";
import cors from "cors";

const mongo = new MongoAdapter('mongodb://127.0.0.1:27017','conti','movimenti');
const app=express();
const url='/conti';
const port=3000;
app.use(express.json());
app.use(cors());
async function visualizza() {
    app.get(url,(req,res)=>{
    

    });
}