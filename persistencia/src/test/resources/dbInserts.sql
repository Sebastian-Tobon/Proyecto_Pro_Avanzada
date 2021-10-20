-- Insert de Administrador
insert into administrador
values (1001, "carlos@email.com", "Carlos Rodriguez", 12345);
insert into administrador
values (1002, "daniel@email.com", "Daniel Toro", 789);
insert into administrador
values (1003, "alba@email.com", "Alba Rosio", 456);
insert into administrador
values (1004, "fernando@email.com", "Fernando Rios", 123);
insert into administrador
values (1005, "jhon@email.com", "Jhon Ruiz", 987);

-- Insert de Categoria
insert into categoria
values (101, "Electrodomesticos");
insert into categoria
values (102, "Alimentos");
insert into categoria
values (103, "Higene");
insert into categoria
values (104, "Ropa");
insert into categoria
values (105, "Calzado");

-- Insert de Ciudad
insert into ciudad
values (1, "Armenia");
insert into ciudad
values (2, "Pereira");
insert into ciudad
values (3, "Manizales");
insert into ciudad
values (4, "Cali");
insert into ciudad
values (5, "Medellin");

-- Insert de Usuario
insert into usuario
values (2001, "maria@email.com", "Maria Cardenas", "B2134", 1);
insert into usuario
values (2002, "mario@email.com", "Mario Fuentes", "C7896", 2);
insert into usuario
values (2003, "sol@email.com", "Sol Ramires", "B4562", 3);
insert into usuario
values (2004, "fabian@email.com", "fabian cruz", "C1238", 4);
insert into usuario
values (2005, "chaves@email.com", "Chaves alzate", "C4662", 5);
insert into usuario
values (null, 'niyora6002@wii999.com', 'Angela Ramirez', 'y%NZi2HIUZ', 1),
       (null, 'jekoxo1677@wii999.com', 'Sofia Cardona', 'MdmxC#yQHX', 2),
       (null, 'gexovo3160@smuvaj.com', 'Daniela Restrepo', 'TQ,c4^sz&-', 3);

-- Insert de Chat
insert into chat
values (124, 2002);
insert into chat
values (125, 2003);
insert into chat
values (126, 2004);
insert into chat
values (127, 2005),
       (123, 2001);

-- Insert de Usuario_num_telefono
insert into usuario_num_telefono
values (2001, "7549856", "Casa");
insert into usuario_num_telefono
values (2002, "3125896789", "Personal");
insert into usuario_num_telefono
values (2003, "75366699", "Trabajo");
insert into usuario_num_telefono
values (2003, "75489633", "Casa");
insert into usuario_num_telefono
values (2004, "3157894562", "Casa");
insert into usuario_num_telefono
values (2005, "3225698742", "Personal");

-- Inserts producto
insert into producto (codigo, nombre, unidades, descripcion, precio, fecha_limite, descuento, usuario_codigo,
                      ciudad_codigo)
values (null, 'Morral camping 95Lts', 50, 'nailon
Importado
Material duradero: fabricado con tela de nailon de alta calidad y material resistente al agua que son duraderos y más resistentes al agua que la mochila de senderismo tradicional.',
        199000, '2021-12-19', 0.27, 2003, 1),
       (null, 'Carpa para Camping para 4 Personas Dome Klimber Camp', 19, 'Carpa Iglú Dome Para 4 Personas Klimber
La Carpa Iglú para 4 Personas Dome Color Gris de Klimber sirve para brindar alojamiento y protección en actividades al aire libre.',
        259000, '2022-03-19', 0.23, 2002, 2),
       (null, 'ASADOR CARBON TIPO BARRIL CHAR-BROIL', 100,
        'Múltiples reguladores que permiten un fácil control del calor y el humo / Las ruedas de 8 pulgadas permiten una mayor movilidad / Con características que incluyen un estante lateral que ofrece mucho espacio para el trabajo de preparación',
        508905, '2022-06-08', 0.15, 2007, 3);

-- Inserts subasta
insert into subasta
values (null, '2021-11-05', 1),
       (null, '2022-01-15', 2),
       (null, '2023-03-25', 3),
       (null, '2022-01-6', 2),
       (null, '2022-01-20', 2);

