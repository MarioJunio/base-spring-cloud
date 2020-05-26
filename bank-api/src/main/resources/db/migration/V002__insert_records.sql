insert into account(id, name, balance)
values (1, 'Mario Junio Marques Martins', 50);

insert into activity(id, owner_account, source_account, target_account, type, value, balance, date)
values
(1, 1, null, null, 'DEPOSIT', 125, 50, '2020-05-26 20:30:58.005'),
(2, 1, null, null, 'DEPOSIT', 45, 50, '2020-05-26 20:31:58.005'),
(3, 1, null, null, 'DEPOSIT', 87, 50, '2020-05-26 20:32:32.000'),
(4, 1, null, null, 'DEPOSIT', 39, 50, '2020-05-26 20:33:10.000'),
(5, 1, null, null, 'DEPOSIT', 424, 50, '2020-05-26 20:34:13.000');
