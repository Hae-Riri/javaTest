package sql;

public class 가격이제일비싼식품의정보출력 {
    /*

-- 코드를 입력하세요
SELECT * from (
    select *
    from food_product
    order by price desc
)
where rownum = 1;


select * from food_product
where price = (select MAX(PRICE) from food_product);

정렬은 최소 nlogn이고, 최댓갑 구하는건 n이어서 최댓값이 더 효율적.


    *
    *
    *
    * */
}
