create schema pharmacy_db;
create table pharmacy_db.Клиенты(
	 Номер_клиента int primary key,
	 Фамилия varchar(20),
	 Имя varchar(20),
	 Отчество varchar(20),
	 Дата_рождения date,
	 Адрес varchar(20),
	 Телефон varchar(20),
	 Категория varchar(20)
);

create table pharmacy_db.Рецепты(
	Номер_рецепта int primary key,
	Номер_клиента int references pharmacy_db.Клиенты(Номер_клиента),
	ФИО_врача varchar(60),
	Диагноз varchar(20)
);

create table pharmacy_db.Заказы(
	Номер_заказа int primary key,
	Номер_рецепта int references pharmacy_db.Рецепты(Номер_рецепта),
	Дата_приема date,
	Дата_изготовления date,
	Дата_выдачи date,
	Стоимость int,
	Статус varchar(15)
);

create table pharmacy_db.Склад(
	Номер_товара int primary key,
	Количество int,
	Критическая_норма int
);

create table pharmacy_db.Изготовляемые_лекарства(
	Номер_лекарства int primary key,
	Номер_на_складе int references pharmacy_db.Склад(Номер_товара),
	Наименование varchar(50),
	Форма varchar(20),
	Стоимость int
);

create table pharmacy_db.Назначения(
	Номер_лекарства int references pharmacy_db.Изготовляемые_лекарства(Номер_лекарства),
	Номер_рецепта int references pharmacy_db.Рецепты(Номер_рецепта),
	Количество int
);

create table pharmacy_db.Готовые_лекарства(
	Номер_лекарства int primary key,
	Номер_на_складе int references pharmacy_db.Склад(Номер_товара),
	Наименование varchar(50),
	Форма varchar(20),
	Стоимость int
);

create table pharmacy_db.Справочник_технологий(
	Номер_технологии int primary key,
	Номер_лекарства int references pharmacy_db.Изготовляемые_лекарства(Номер_лекарства),
	Способ_приготовления varchar(10000),
	Время_приготовления_в_часах int
);

create table pharmacy_db.Компоненты(
	Номер_компонента int primary key,
	Номер_на_складе int references pharmacy_db.Склад(Номер_товара),
	Наименование varchar(50),
	Форма varchar(20),
	Стоимость int
);

create table pharmacy_db.Состав(
	Номер_ингредиента int references pharmacy_db.Компоненты(Номер_компонента),
	Номер_лекарства int references pharmacy_db.Изготовляемые_лекарства(Номер_лекарства),
	Количество_в_мг int
);

create table pharmacy_db.Статистика(
	Номер_товара int references pharmacy_db.Склад(Номер_товара),
	Дата date,
	Продано int
);