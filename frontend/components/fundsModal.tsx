import { DialogHeader, DialogTitle } from "@/components/ui/dialog"


export default function FundsModal() {
    return (
        <DialogHeader>
            <DialogTitle className="text-center">New Fund</DialogTitle>
            <form>
                <div className="flex flex-col gap-6">
                    <h3>Funds Forms</h3>
                </div>
            </form>
        </DialogHeader>
    )
}
