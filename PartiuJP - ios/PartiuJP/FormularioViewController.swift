//
//  ViewController.swift
//
//  Created by admin on 16/08/16.
//  Copyright © 2016 admin. All rights reserved.
//

import UIKit


class FormularioViewController: UIViewController {
    
    
    var delegate: ListarTableViewController!
    

    @IBOutlet weak var Tfnome: UITextField!
    
    @IBOutlet weak var local: UITextField!
    
    //@IBOutlet weak var Dpdata: UIDatePicker!
    
    @IBOutlet weak var lbvalor: UILabel!
    
    @IBOutlet weak var svalor: UIStepper!
    
    @IBOutlet weak var stInteresse: UISwitch!
    
    @IBAction func definirValor(sender: AnyObject) {
        self.lbvalor.text = String(Int(self.svalor.value))
    }


    override func viewDidLoad() {
        super.viewDidLoad()
            
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.Save, target: self, action: #selector(salvar))
    }
    
    func salvar() {
        print("Funcionou!! Eba")
        let varvalor = Int(self.svalor.value)
        self.delegate.salvar(Evento(nome: self.Tfnome.text!, local: self.local.text!,valor: varvalor))
        
        self.navigationController?.popViewControllerAnimated(true)
    }

}

