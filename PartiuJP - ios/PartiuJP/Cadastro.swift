//
//  Cadastro.swift
//  
//
//  Created by admin on 16/08/16.
//  Copyright © 2016 admin. All rights reserved.
//

import Foundation

class Cadastro: NSObject, NSCoding {
    var lista: Array<Evento>!
    
    override init(){
        self.lista = Array<Evento>()
    }
    
    required init?(coder aDecoder: NSCoder){
        self.lista = aDecoder.decodeObjectForKey("lista") as! Array<Evento>
        
    }
    
    func encodeWithCoder(aCoder:NSCoder){
        aCoder.encodeObject(self.lista, forKey: "lista")
    }
    
    
    
    func add(ev: Evento) {
        self.lista.append(ev)
    }
    
    func get(index: Int) -> Evento{
        return self.lista[index]
    }
    
    func del(index:Int) {
        self.lista.removeAtIndex(index)
    }
    
    func quantidade() -> Int{
        return self.lista.count
    }
    func swap(origem: Int, destino:Int){
        let aux = self.lista[origem]
        self.lista[origem] = self.lista[destino]
        self.lista[destino] = aux
    }
    
}