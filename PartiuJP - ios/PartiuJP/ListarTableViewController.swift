//
//  ListarTableViewController.swift
//  
//
//  Created by admin on 16/08/16.
//  Copyright © 2016 admin. All rights reserved.
//

import UIKit

class ListarTableViewController: UITableViewController{
    
    var cadastro = Cadastro()
    
    
    func path()->String {
        return NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask, true)[0]
    }
    
    func arquivo() -> String{
        return "\(self.path())/arquivo"
    }

    
    func salvar(ev: Evento) {
        self.cadastro.add(ev)
        print(self.arquivo())
        NSKeyedArchiver.archiveRootObject(self.cadastro, toFile: self.arquivo())
        tableView.reloadData()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        self.navigationItem.leftBarButtonItem = self.editButtonItem()
        
        tableView.dataSource = self
        tableView.delegate = self
        
        let obj = NSKeyedUnarchiver.unarchiveObjectWithFile(self.arquivo())
        if(obj != nil){
            
            self.cadastro = obj as! Cadastro
        }
        else{
            self.cadastro = Cadastro()
        }
    
        func viewWillDisappear(animated: Bool){
            
            super.viewWillDisappear(true)
        
            
        
        }

        
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(true)
        
        self.tableView.reloadData()
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.cadastro.quantidade()
    }

    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("celula", forIndexPath: indexPath) as! CelulaTableViewCell

        cell.lbnome.text = String(self.cadastro.get(indexPath.row))
        cell.ev = self.cadastro.get(indexPath.row)

        return cell
    }

    
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {

        return true
    }
 

    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            self.cadastro.del(indexPath.row)
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        }
        
    }

    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {
        self.cadastro.swap(fromIndexPath.row, destino: toIndexPath.row)
        
    }

    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if (segue.identifier == "listar_formulario"){
            let view = segue.destinationViewController as! FormularioViewController
            view.delegate = self
        }
    }
    



}
