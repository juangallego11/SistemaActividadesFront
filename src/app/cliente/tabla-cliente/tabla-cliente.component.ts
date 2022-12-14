import { Component, OnInit, ViewChild, Input, OnChanges, Output, EventEmitter } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Usuario } from 'src/app/model/Usuario';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TablaClienteService } from './tabla-cliente.service';
import { ConfirmDialogComponent, ConfirmDialogModel } from 'src/app/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-tabla-cliente',
  templateUrl: './tabla-cliente.component.html',
  styleUrls: ['./tabla-cliente.component.css'],
  providers: [TablaClienteService]
})
export class TablaClienteComponent implements OnInit, OnChanges {

  public listaUsuarios: Array<any> = new Array<any>();

  displayedColumns: string[] = ['idCliente', 'username', 'apellido', 'telefono','correo', 'opciones'];
  dataSource = new MatTableDataSource<Usuario>(this.listaUsuarios);

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(private tablaService: TablaClienteService, public dialog: MatDialog, private _snackBar: MatSnackBar) { }

  @Input() datoUsuario: any;

  @Output() editUsuario = new EventEmitter<any>()

  ngOnInit(): void {
    this.obtenerUsuarios();
    this.paginator._intl.itemsPerPageLabel = 'Filas por página'
  }

  ngOnChanges() {
    if (this.datoUsuario != null || this.datoUsuario != undefined) {
      this.agregarUsuario(this.datoUsuario);
    }
  }

  public agregarUsuario(u: any): void {
    this.listaUsuarios.push(u)
    this.dataSource = new MatTableDataSource(this.listaUsuarios);
    this.dataSource.paginator = this.paginator;
    this.openSnackBar('Cliente guardado con exito', 'Exito')
  }

  public obtenerUsuarios(): void {
    this.tablaService.getUsers().subscribe(res => {
      if (res != null) {
        console.log(res);
        this.listaUsuarios = res
        this.dataSource = new MatTableDataSource(this.listaUsuarios);
        this.dataSource.paginator = this.paginator;
      }
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  confirmDialog(data: any): void {
    const message = 'Estas seguro de eliminar al empleado ?';

    const dialogData = new ConfirmDialogModel("Confirmar acción", message);

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.eliminarUsuario(data.idEmpleado);
      }
    });
  }

  public eliminarUsuario(id: number): void {
    this.tablaService.deleteUsers(id).subscribe(res => {
      if (res != null) {
        this.obtenerUsuarios();
        this.openSnackBar('Empleado eliminado correctamente','Exito');
      }
    }, error => {
      console.log(error.error.message)
      const info = new ConfirmDialogModel("Confirmar acción", error.error.message);
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        maxWidth: "400px",
        data: info
      });
  
      dialogRef.afterClosed().subscribe(dialogResult => {
        if (dialogResult) {
        }
      });
    })
  }

  public editarUsuario(data: any): void {
    console.log(data)
    this.editUsuario.emit(data)
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 3000,
    });
  }
}
