-- Insert de Administrador
insert into administrador values (1001, "carlos@email.com", "Carlos Rodriguez", 12345);
insert into administrador values (1002, "daniel@email.com", "Daniel Toro", 789);
insert into administrador values (1003, "alba@email.com", "Alba Rosio", 456);
insert into administrador values (1004, "fernando@email.com", "Fernando Rios", 123);
insert into administrador values (1005, "jhon@email.com", "Jhon Ruiz", 987);

-- Insert de Categoria
insert into categoria values (101, "Electrodomesticos");
insert into categoria values (102, "Alimentos");
insert into categoria values (103, "Higene");
insert into categoria values (104, "Ropa");
insert into categoria values (105, "Calzado");

-- Insert de Ciudad
insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Pereira");
insert into ciudad values (3, "Manizales");
insert into ciudad values (4, "Cali");
insert into ciudad values (5, "Medellin");

-- Insert de Usuario
insert into usuario values (2001, "maria@email.com", "Maria Cardenas",  "B2134","maria2021", 1);
insert into usuario values (2002, "mario@email.com", "Mario Fuentes",  "C7896","mario45", 2);
insert into usuario values (2003, "sol@email.com", "Sol Ramires",  "B4562","sol91", 3);
insert into usuario values (2004, "fabian@email.com", "fabian cruz",  "C1238","fabian1889", 4);
insert into usuario values (2005, "chaves@email.com", "Chaves alzate",  "C4662","chavesss", 5);
insert into usuario values (2006, 'niyora6002@wii999.com', 'Angela Ramirez',  'y%NZi2HIUZ',"angel001", 1);
insert into usuario values (2007, 'jekoxo1677@wii999.com', 'Sofia Cardona',  'MdmxC#yQHX',"sofi23", 2);
insert into usuario values(2008, 'gexovo3160@smuvaj.com', 'Daniela Restrepo',  'TQ,c4^sz&-',"dani2002", 3);

-- Insert de Producto
insert into producto (codigo, descripcion, descuento, fecha_limite, nombre, precio, unidades, ciudad_codigo, vendedor_codigo )
values (1, 'nailon Importado Material duradero: fabricado con tela de nailon de alta calidad y material resistente al agua que son duraderos y más resistentes al agua que la mochila de senderismo tradicional.', 0.27, '2021-12-19',
        'Morral Camping 95Lts', 199000, 50, 1, 2001),
       (2, 'Carpa Iglú Dome Para 4 Personas Klimber La Carpa Iglú para 4 Personas Dome Color Gris de Klimber sirve para brindar alojamiento y protección en actividades al aire libre.',
        0.23 , '2022-03-19', 'Carpa para Camping para 4 Personas Dome Klimber Camp', 259000, 19, 2, 2002),
       (3, 'Múltiples reguladores que permiten un fácil control del calor y el humo / Las ruedas de 8 pulgadas permiten una mayor movilidad / Con características que incluyen un estante lateral que ofrece mucho espacio para el trabajo de preparación',
        0.15,'2022-06-08' , 'ASADOR CARBON TIPO BARRIL CHAR-BROIL', 508000, 100, 3, 2007),
        (4, 'Todo terreno', 0.22, '2021-12-31', 'bicicleta deportiva', 2500000, 20, 4, 2004);

-- Insert de Chat
insert into chat values (123, 2, 2001);
insert into chat values (124, 1, 2002);
insert into chat values (125, 3, 2003);
insert into chat values (126, 1, 2004);
insert into chat values (127, 2, 2005);

-- Insert de Usuario_num_telefono
insert into usuario_num_telefono values (2001, "7549856", "Casa");
insert into usuario_num_telefono values (2002, "3125896789", "Personal");
insert into usuario_num_telefono values (2003, "75366699", "Trabajo");
insert into usuario_num_telefono values (2003, "75489633", "Casa");
insert into usuario_num_telefono values (2004, "3157894562", "Casa");
insert into usuario_num_telefono values (2005, "3225698742", "Personal");

-- Insert de Comentario
insert into comentario values (100, 3.8, '2021-10-19', "Muy debil la tela", "Lamentamos los inconvenientes", 1, 2002);
insert into comentario values (101, 4.8, '2021-9-9', "Excelente producto", "Agradecemos su compra", 2, 2003);
insert into comentario values (102, 2.5, '2021-3-29', "Mucha demora en el envio", "Lamentamos los inconvenientes en el envio del producto", 3, 2004);

--Insert Categoria
insert into categoria values (1, "Tecnologia");
insert into categoria values (2, "Electrodomestico");
insert into categoria values (3, "Ropa");
insert into categoria values (4, "Accesorios");

--Insert categoria_productos
insert into categoria_productos values (1, 2);
insert into categoria_productos values (2, 1);
insert into categoria_productos values (3, 3);
insert into categoria_productos values (4, 2);
insert into categoria_productos values (4, 1);

-- Inserts subasta
insert into subasta
values (201, '2021-11-05', 1),
       (202, '2022-01-15', 2),
       (203, '2023-03-25', 3),
       (204, '2022-01-6', 2),
       (205, '2022-01-20', 2);

-- Inserts Compra
insert into compra
values (100, '2021-11-05', "Efectivo", 2001),
        (101, '2021-10-15', "Targeta de Credito", 2002),
        (102, '2021-11-06', "Efectivo", 2003),
        (103, '2021-12-25', "Targeta de Credito", 2004),
        (104, '2021-12-01', "Targeta de Credito", 2004);

-- Inserts DetalleCompra
insert into detalle_compra
values (100, 150.000, 60, 101, 1),
       (101, 150.000, 20, 100, 2),
       (102, 150.000, 80, 102, 3),
       (103, 150.000, 40, 100, 3),
       (104, 150.000, 10, 104, 1);

-- Inserts DetalleSubasta
insert into detalle_subasta
values (300, '2021-10-05', 600000, 201, 2001),
       (301, '2021-08-15', 2500000, 202, 2002),
       (302, '2021-12-07', 1600000, 201, 2003),
       (303, '2021-11-22', 900000, 203, 2004),
       (304, '2021-11-11', 500000, 204, 2007),
       (305, '2021-01-05', 1230000, 205, 2006);

-- Inserts Mensaje
insert into mensaje
values (1000, 'Emiliano Gil', '2021-12-07', "Excelente producto, 10/10", 124),
       (1001, 'Sofia Cardona', '2021-01-27', "Cuando regresa la promocion, Excelente producto", 123),
       (1002, 'Fernando Ramirez', '2021-10-17', "Puedo Cambiar mis productos", 125),
       (1003, 'Jhonatan Garcia', '2021-08-07', "Con cuanta garantia trae el producto de la Carpa", 124),
       (1004, 'Angie Carmona', '2021-05-30', "Donde puedo adquirir los productos presencial", 127),
       (1005, 'Johan Tamara', '2021-11-11', "Excelente servicio", 126);

-- Insert Productos Favoritos Usuario
insert into usuario_usuario_list_productos_fav
values ('2001', 1);
insert into usuario_usuario_list_productos_fav
values ('2001', 2);
insert into usuario_usuario_list_productos_fav
values ('2003', 3);
insert into usuario_usuario_list_productos_fav
values ('2004', 2);
