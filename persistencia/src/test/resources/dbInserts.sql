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
insert into usuario values (2001, "Maria Cardenas", "maria@email.com", "B2134", "Vendedor", 1);
insert into usuario values (2002, "Mario Fuentes", "mario@email.com", "C7896", "Vendedor", 2);
insert into usuario values (2003, "Sol Ramires", "sol@email.com", "B4562", "Comprador", 3);
insert into usuario values (2004, "fabian cruz", "fabian@email.com", "C1238", "Comprador", 4);
insert into usuario values (2005, "Chaves alzate", "chaves@email.com", "C4662", "Comprador", 5);
insert into usuario values (2006, 'Angela Ramirez', 'niyora6002@wii999.com', 'y%NZi2HIUZ', "Comprador", 1);
insert into usuario values (2007, 'Sofia Cardona', 'jekoxo1677@wii999.com', 'MdmxC#yQHX', "Vendedor", 2);
insert into usuario values(2008, 'Daniela Restrepo', 'gexovo3160@smuvaj.com', 'TQ,c4^sz&-', "Comprador", 3);

-- Insert de Producto
insert into producto (codigo, nombre, unidades, descripcion, precio, fecha_limite, descuento, usuario_codigo, ciudad_codigo)
values (1, 'Morral camping 95Lts', 50, 'nailon Importado Material duradero: fabricado con tela de nailon de alta calidad y material resistente al agua que son duraderos y más resistentes al agua que la mochila de senderismo tradicional.',
        199000, '2021-12-19', 0.27, 2003, 1),
       (2, 'Carpa para Camping para 4 Personas Dome Klimber Camp', 19, 'Carpa Iglú Dome Para 4 Personas Klimber
La Carpa Iglú para 4 Personas Dome Color Gris de Klimber sirve para brindar alojamiento y protección en actividades al aire libre.',
        259000, '2022-03-19', 0.23, 2002, 2),
       (3, 'ASADOR CARBON TIPO BARRIL CHAR-BROIL', 100,
        'Múltiples reguladores que permiten un fácil control del calor y el humo / Las ruedas de 8 pulgadas permiten una mayor movilidad / Con características que incluyen un estante lateral que ofrece mucho espacio para el trabajo de preparación',
        508905, '2022-06-08', 0.15, 2007, 3);

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

-- Inserts subasta
insert into subasta
values (201, '2021-11-05', 1),
       (202, '2022-01-15', 2),
       (203, '2023-03-25', 3),
       (204, '2022-01-6', 2),
       (205, '2022-01-20', 2);