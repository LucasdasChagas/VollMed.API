Alter table medicos add column  ativos tinyint not null;
Alter table pacientes add column  ativos tinyint not null;
update medicos set ativos = 1;
update pacientes set ativos = 1;