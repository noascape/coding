import time
import threading
from flet import *
from flet import colors, icons, alignment, border

class Toast:
    def __init__(
        self,
        page: Page,
        icon,
        msg_title,
        msg_desc,
        trigger,
        bgcolor: str = None,
        auto_close: int = 5,
    ):
        self.page = page
        self.icon = icon
        self.msg_title = msg_title
        self.msg_desc = msg_desc
        self.trigger = trigger
        assert hasattr(
            self.trigger, "on_click"
        ), "Control must contain `on_click` attribute"
        self.trigger.on_click = lambda x: threading.Thread(
            target=self._update_visibility, daemon=True
        ).start()

        self.bgcolor = bgcolor
        self.auto_close = auto_close

    def _update_visibility(self):
        self.stack.opacity = 1
        self.page.update()
        time.sleep(self.auto_close)
        self.stack.opacity = 0
        self.page.update()

    def struct(self):
        main_stack = Stack(expand=True)
        main_stack.controls = [self.toast_container()]
        self.page.add(main_stack)

    def toast_container(self):
        header = Row(
            controls=[
                Row([Icon(self.icon), Text(self.msg_title)]),
                IconButton(
                    icons.CLOSE_OUTLINED,
                    on_click=lambda x: threading.Thread(
                        target=self._close, daemon=True
                    ).start(),
                ),
            ],
            alignment="spaceBetween",
        )

        toast_content = Text(self.msg_desc)

        self.container = Container(
            content=Column([header, Divider(), toast_content]),
            width=400,
            bgcolor=self.bgcolor,
            border_radius=10,
            padding=10,
            border=border.all(0.5, colors.BLACK12),
            right=0,
            bottom=0,
            expand=True,
        )

        self.stack = Stack(
            width=self.page.window_width,
            height=self.page.window_height,
            controls=[self.container],
            opacity=0,
            animate_opacity=500,
            expand=True,
        )
        return self.stack