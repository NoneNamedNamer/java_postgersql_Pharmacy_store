insert into pharmacy_db.Клиенты values(1, 'Гурев', 'Алексей', 'Антонович', '15.08.2000', 'Алеутская, 26', '89662839912', 'Льготник')
insert into pharmacy_db.Клиенты values(16, 'Гурев', 'Алексей', 'Антонович', '15.08.2000', 'Алеутская, 26', '89662839912', 'Льготник')
select * from pharmacy_db.Клиенты

insert into pharmacy_db.Рецепты values(42, 2, 'Кириллов Евгений Артемович', 'Депрессия')
insert into pharmacy_db.Рецепты values(12, 2, 'Кириллов Евгений Артемович', 'Шизофрения')
insert into pharmacy_db.Рецепты values(88, 16, 'Кириллов Евгений Артемович', 'Шизофрения')
select * from pharmacy_db.Рецепты

insert into pharmacy_db.Заказы values(1, 65, '12.05.2023', '22.05.2023', '01.06.2023', 140, 'Выполнен')
insert into pharmacy_db.Заказы values(2, 42, '12.05.2023', '25.05.2023', '07.06.2023', 180, 'В ожидании')
insert into pharmacy_db.Заказы values(3, 55, '12.05.2023', '22.05.2023', '08.06.2023', 200, 'В ожидании')
insert into pharmacy_db.Заказы values(4, 12, '12.05.2023', '22.05.2023', '08.06.2023', 150, 'Выполнен')
insert into pharmacy_db.Заказы values(5, 12, '12.05.2023', '22.05.2023', '08.06.2023', 150, 'Выполнен')
insert into pharmacy_db.Заказы values(6, 88, '12.05.2023', '22.05.2023', '08.06.2023', 150, 'В ожидании')
select * from pharmacy_db.Заказы

insert into pharmacy_db.Склад values(1, 25, 10)
insert into pharmacy_db.Склад values(4, 20, 10)
insert into pharmacy_db.Склад values(8, 15, 5)
insert into pharmacy_db.Склад values(10, 30, 10)
insert into pharmacy_db.Склад values(99, 30, 10)
select * from pharmacy_db.Склад

insert into pharmacy_db.Изготовляемые_лекарства values(5, 1, 'Фенибут', 'Таблетки', 110)
insert into pharmacy_db.Изготовляемые_лекарства values(23, 8, 'Серлифт', 'Таблетки', 140)
insert into pharmacy_db.Изготовляемые_лекарства values(24, 4, 'Бетамакс', 'Таблетки', 150)
insert into pharmacy_db.Изготовляемые_лекарства values(15, 10, 'Галоперидол', 'Ампулы', 150)
insert into pharmacy_db.Изготовляемые_лекарства values(16, 99, 'Галоперидол max', 'Ампулы', 160)
select * from pharmacy_db.Изготовляемые_лекарства

insert into pharmacy_db.Назначения values(5, 65, 2)
insert into pharmacy_db.Назначения values(23, 42, 1)
insert into pharmacy_db.Назначения values(24, 55, 1)
insert into pharmacy_db.Назначения values(15, 12, 1)
insert into pharmacy_db.Назначения values(15, 12, 1)
insert into pharmacy_db.Назначения values(15, 88, 1)
insert into pharmacy_db.Назначения values(16, 12, 1)
insert into pharmacy_db.Назначения values(16, 88, 5)
select * from pharmacy_db.Назначения

--Функция на рецепты--
create or replace function pharmacy_db.recipes_delete()
returns trigger as
$$
begin
	if (select count(Номер_лекарства) from pharmacy_db.Назначения where Назначения.Номер_рецепта=old.Номер_рецепта)>0 then
		delete from pharmacy_db.Назначения where Назначения.Номер_рецепта=old.Номер_рецепта;
	end if;
	if (select count(Номер_заказа) from pharmacy_db.Заказы where Заказы.Номер_рецепта=old.Номер_рецепта)>0 then
		delete from pharmacy_db.Заказы where Заказы.Номер_рецепта=old.Номер_рецепта;
	end if;
	return old;
end;
$$
language 'plpgsql';
--Триггер на удаление рецептов--
create trigger recipes_delete
before delete on pharmacy_db.Рецепты
for each row
execute function pharmacy_db.recipes_delete()

--Функция на лекарства--
create or replace function pharmacy_db.meds_delete()
returns trigger as
$$
begin
	if (select count(Номер_лекарства) from pharmacy_db.Назначения where Назначения.Номер_лекарства=old.Номер_лекарства)>0 then
		delete from pharmacy_db.Назначения where Назначения.Номер_лекарства=old.Номер_лекарства;
	end if;
	return old;
end;
$$
language 'plpgsql';
--Триггер на удаление лекарств--
create trigger meds_delete
before delete on pharmacy_db.Изготовляемые_лекарства
for each row
execute function pharmacy_db.meds_delete()

--Функция на удаление клиентов--
create or replace function pharmacy_db.clients_delete()
returns trigger as
$$
begin
	if (select count(Номер_рецепта) from pharmacy_db.Рецепты where Рецепты.Номер_клиента=old.Номер_клиента)>0 then
		if (select count(Номер_рецепта) from pharmacy_db.Назначения inner join pharmacy_db.Рецепты using(Номер_рецепта)
		where Рецепты.Номер_клиента=old.Номер_клиента)>0 then
			delete from pharmacy_db.Назначения where Назначения.Номер_рецепта in
			(select Номер_рецепта from pharmacy_db.Рецепты where Рецепты.Номер_клиента=old.Номер_клиента);
		end if;
		if (select count(Номер_рецепта) from pharmacy_db.Заказы inner join pharmacy_db.Рецепты using(Номер_рецепта)
		where Рецепты.Номер_клиента=old.Номер_клиента)>0 then
			delete from pharmacy_db.Заказы where Заказы.Номер_рецепта in
			(select Номер_рецепта from pharmacy_db.Рецепты where Рецепты.Номер_клиента=old.Номер_клиента);
		end if;
		delete from pharmacy_db.Рецепты where Рецепты.Номер_клиента=old.Номер_клиента;
	end if;
	return old;
end;
$$
language 'plpgsql';
--Триггер на удаление клиентов--
create trigger clients_delete
before delete on pharmacy_db.Клиенты
for each row
execute function pharmacy_db.clients_delete()

--Функция на удаление со склада--
create or replace function pharmacy_db.delete_from_warehouse()
returns trigger as
$$
begin
	if (select count(Номер_лекарства) from pharmacy_db.Изготовляемые_лекарства where Изготовляемые_лекарства.Номер_на_складе=old.Номер_товара)>0 then
		if (select count(Номер_лекарства) from pharmacy_db.Назначения inner join pharmacy_db.Изготовляемые_лекарства using(Номер_лекарства)
		where Изготовляемые_лекарства.Номер_на_складе=old.Номер_товара)>0 then
			delete from pharmacy_db.Назначения where Назначения.Номер_лекарства in
			(select Номер_лекарства from pharmacy_db.Изготовляемые_лекарства where Изготовляемые_лекарства.Номер_на_складе=old.Номер_товара);
		end if;
		delete from pharmacy_db.Изготовляемые_лекарства where Изготовляемые_лекарства.Номер_на_складе=old.Номер_товара;
	end if;
	return old;
end;
$$
language 'plpgsql';
--Триггер на удаление со склада--
create trigger delete_from_warehoues
before delete on pharmacy_db.Склад
for each row
execute function pharmacy_db.delete_from_warehouse()

--Функция обновления количества на складе--
create or replace function pharmacy_db.update_quantity()
returns trigger as
$$
declare
	appoint integer;
begin
	appoint:=new.Количество;
	update pharmacy_db.Склад
	set Количество = Количество - appoint
	where Номер_товара=(
		select Номер_на_складе
		from pharmacy_db.Изготовляемые_лекарства
		where Номер_лекарства=new.Номер_лекарства
	);
	return new;
end;
$$
language 'plpgsql';
--Триггер для обновления на складе--
create trigger update_quantity
after insert on pharmacy_db.Назначения
for each row
execute function pharmacy_db.update_quantity()

alter table pharmacy_db.Склад add check (Количество > Критическая_норма)