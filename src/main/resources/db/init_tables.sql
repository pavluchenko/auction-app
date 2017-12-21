INSERT INTO public.role(
            id, name)
    VALUES (1, 'ROLE_USER');

INSERT INTO public.users(
            id, email, password_hash, rating, password_hash_confirm)
    VALUES (1, 'foo@bar.com', 'pass', '0', 'pass');

INSERT INTO public.users_role(
            role_id, user_id, id)
    VALUES (1, 1, 1);

INSERT INTO public.subcategory(
            id, name, category_id)
    VALUES (1, 'name', 1);

INSERT INTO public.lot(
            id, name, user_id, description, category_id, bayout_price, photo,
            min_price, max_price)
    VALUES (1, 'name', 1, 'description', 1, 1000, 'photo',
            100, 1000);

