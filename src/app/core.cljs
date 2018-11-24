(ns app.core
  (:require [hx.react :as hx :include-macros true]
            [re-frame.core :as rf]
            [app.hooks :refer [<-state <-sub]]))

(rf/reg-event-db
 :inc
 (fn [db _]
   (update db :counter (fnil inc 0))))

(rf/reg-sub
 :counter
 (fn [db _]
   (:counter db 0)))

(hx/defnc ReframeHook [_]
  (let [count (<-sub [:counter])]
    [:div
     [:strong "re-frame <-sub Hook:"] " "
     [:button {:on-click #(rf/dispatch [:inc])
               :class "bg-blue text-white px-3 py-2 rounded focus:outline-none focus:shadow-outline"}
      count]]))

(hx/defnc Counter []
  (let [count (<-state 0)]
    [:div
     [:strong "<-state Hook:"] " "
     [:button {:on-click #(swap! count inc)
               :class "bg-blue text-white px-3 py-2 rounded focus:outline-none focus:shadow-outline"}
      @count]]))

(hx/defnc App [{:keys [name]}]
  [:div "Hello "
   [:span {:style {:font-weight "bold"}} name] "!"
   [Counter]
   [ReframeHook]])
