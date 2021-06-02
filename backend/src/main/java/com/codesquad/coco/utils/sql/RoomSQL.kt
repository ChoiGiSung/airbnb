package com.codesquad.coco.utils.sql;

const val FIND_ALL_PRICE_BY_SEARCH_REQUIREMENT: String = """
select rm.price_per_date 
from room rm 
inner join location l on rm.location_id = l.id 
where rm.id not in (select distinct rv.room_id
					from reservation rv
					where :check_in between rv.check_in and rv.check_out
					or :check_out between rv.check_in and rv.check_out
                    or rv.check_in  between :check_in and :check_out 
                    or rv.check_out between :check_in and :check_out )
and l.city_name = :city_name
"""

//fixme : 서브쿼리 rv에서 stauts가 reserved인 애들만 가지고 온다.price도 동일
const val FIND_ALL_ROOM_BY_SEARCH_REQUIREMENT: String = """
select distinct rm.id as rm_id,rm.name as rm_name,rm.price_per_date as rm_price_per_date,rm.description as rm_description, rm.bed as rm_bed,rm.max_guest as rm_max_guest,rm.bathroom as rm_bathroom,rm.type as rm_type,
h.name as h_name,h.profile_image_url as h_profile_image_url,
l.city_name as l_city_name,l.latitude as l_latitude,l.longitude as l_longitude,
ac.cleaning_fee as ac_cleaning_fee, ac.lodgment_fee_percent as ac_lodgment_fee_percent, ac.service_fee_percent as ac_service_fee_percent, ac.week_sale_percent as ac_week_sale_percent,
rw.star as rw_star, rw.review as rw_review,
wl.user_id as wl_user_id, wl.room_id as wl_room_id, wl.wish as wl_wish
from room rm
left join reservation rv on rm.id = rv.room_id
inner join location l on rm.location_id = l.id
inner join host h on h.id = rm.host_id
inner join review rw on rw.id = rm.review_id
inner join additional_cost ac on ac.id = rm.additional_cost_id
left join wish_list wl on wl.room_id = rm.id
where rm.id not in (select distinct rv.room_id
					from reservation rv
					where ( :check_in between rv.check_in and date_sub(rv.check_out,interval 1 day)
					or :check_out between date_add(rv.check_in,interval 1 day) and rv.check_out
                    or rv.check_in  between :check_in and date_sub(:check_out,interval 1 day) 
                    or rv.check_out between date_add(:check_in,interval 1 day) and :check_out )
                    and rv.status = 'RESERVED'
                    )
and l.city_name = :city_name
and rm.max_guest >= :adult + :child
and rm.price_per_date between :price_min and :price_max;
"""
const val FIND_ALL_RESERVATION_BY_ROOM_ID: String = """
select rv.id as rv_id, rv.user_id as rv_user_id, rv.adult as rv_adult, rv.child as rv_child,
 rv.baby as rv_baby, rv.check_in as rv_check_in, rv.check_out as rv_check_out,rv.total_price as rv_total_price
from reservation rv
where rv.room_id = :room_id
and rv.status = 'RESERVED';
"""

const val FIND_ROOM_BY_ROOM_ID: String = """
select rm.id as rm_id,rm.name as rm_name,rm.price_per_date as rm_price_per_date,rm.description as rm_description, rm.bed as rm_bed,rm.max_guest as rm_max_guest,rm.bathroom as rm_bathroom,rm.type as rm_type,
h.name as h_name,h.profile_image_url as h_profile_image_url,
l.city_name as l_city_name,l.latitude as l_latitude,l.longitude as l_longitude,
ac.cleaning_fee as ac_cleaning_fee, ac.lodgment_fee_percent as ac_lodgment_fee_percent, ac.service_fee_percent as ac_service_fee_percent, ac.week_sale_percent as ac_week_sale_percent,
rw.star as rw_star, rw.review as rw_review,
wl.user_id as wl_user_id, wl.room_id as wl_room_id, wl.wish as wl_wish
from room rm
inner join location l on rm.location_id = l.id
inner join host h on h.id = rm.host_id
inner join review rw on rw.id = rm.review_id
inner join additional_cost ac on ac.id = rm.additional_cost_id
left join wish_list wl on wl.room_id = rm.id
where rm.id = :room_id
"""

//todo : wish list 부분 user를 넣었을 때 생각


const val FIND_ALL_ROOM_BY_SEARCH_LOCATION_REQUIREMENT: String = """
select distinct rm.id as rm_id,rm.name as rm_name,rm.price_per_date as rm_price_per_date,rm.description as rm_description, rm.bed as rm_bed,rm.max_guest as rm_max_guest,rm.bathroom as rm_bathroom,rm.type as rm_type,
h.name as h_name,h.profile_image_url as h_profile_image_url,
l.city_name as l_city_name,l.latitude as l_latitude,l.longitude as l_longitude,
ac.cleaning_fee as ac_cleaning_fee, ac.lodgment_fee_percent as ac_lodgment_fee_percent, ac.service_fee_percent as ac_service_fee_percent, ac.week_sale_percent as ac_week_sale_percent,
rw.star as rw_star, rw.review as rw_review,
wl.user_id as wl_user_id, wl.room_id as wl_room_id, wl.wish as wl_wish
from room rm
left join reservation rv on rm.id = rv.room_id
inner join location l on rm.location_id = l.id
inner join host h on h.id = rm.host_id
inner join review rw on rw.id = rm.review_id
inner join additional_cost ac on ac.id = rm.additional_cost_id
left join wish_list wl on wl.room_id = rm.id
where rm.id not in (select distinct rv.room_id
					from reservation rv
					where ( :check_in between rv.check_in and date_sub(rv.check_out,interval 1 day)
					or :check_out between date_add(rv.check_in,interval 1 day) and rv.check_out
                    or rv.check_in  between :check_in and date_sub(:check_out,interval 1 day) 
                    or rv.check_out between date_add(:check_in,interval 1 day) and :check_out )
                    and rv.status = 'RESERVED'
                    )
and rm.max_guest >= :adult + :child
and rm.price_per_date between :price_min and :price_max
and l.latitude between :latitude and :latitude + 0.05 
and l.longitude  between :longitude and :longitude + 0.05;
"""