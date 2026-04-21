CREATE TABLE IF NOT EXISTS сырые_события_заказов (
    идентификатор UUID PRIMARY KEY,
    номер_заказа VARCHAR(255) NOT NULL,
    номер_телефона_покупателя VARCHAR(255) NOT NULL,
    описание_заказа TEXT NOT NULL,
    сумма_заказа NUMERIC(19, 2) NOT NULL,
    дата_события TIMESTAMP NOT NULL
);
